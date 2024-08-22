package com.ssk.run.network

import com.ssk.core.data.networking.constructRoute
import com.ssk.core.data.networking.delete
import com.ssk.core.data.networking.get
import com.ssk.core.data.networking.safeCall
import com.ssk.core.domain.run.RemoteRunDataSource
import com.ssk.core.domain.run.Run
import com.ssk.core.domain.util.DataError
import com.ssk.core.domain.util.EmptyDataResult
import com.ssk.core.domain.util.Result
import com.ssk.core.domain.util.map
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class KtorRemoteDataSource(
    private val httpClient: HttpClient
) : RemoteRunDataSource {

    override suspend fun getRuns(): Result<List<Run>, DataError.Network> {
        return httpClient.get<List<RunDto>>(
            route = "/runs",
        ).map { runDtos ->
            runDtos.map {
                it.toRun()
            }
        }
    }

    override suspend fun postRun(run: Run, mapPicture: ByteArray): Result<Run, DataError.Network> {
        val createRunRequestJson = Json.encodeToString(
            run.toCreateRunRequest()
        )
        val response = safeCall<RunDto> {
            httpClient.submitFormWithBinaryData(
                url = constructRoute("/run"),
                formData =
                formData {
                    append("MAP_PICTURE", mapPicture, Headers.build {
                        append(HttpHeaders.ContentType, "image/jpeg")
                        append(HttpHeaders.ContentDisposition, "filename=mappicture.jpg")
                    })
                    append("RUN_DATA", createRunRequestJson, Headers.build {
                        append(HttpHeaders.ContentType, "text/plain")
                        append(HttpHeaders.ContentDisposition, "form-data;\"RUN_DATA\"")
                    })
                }
            ) {
                method = HttpMethod.Post
            }
        }
        return response.map {
            it.toRun()
        }
    }

    override suspend fun deleteRun(id: String): EmptyDataResult<DataError.Network> {
       return httpClient.delete(
           route = "/run",
           queryParameters = mapOf(
               "id" to id
           )
       )
    }

}