package com.ssk.core.domain.run

import com.ssk.core.domain.util.DataError
import com.ssk.core.domain.util.EmptyDataResult
import com.ssk.core.domain.util.Result

interface RemoteRunDataSource {

    suspend fun getRuns(): Result<List<Run>, DataError.Network>

    suspend fun postRun(run: Run, mapPicture: ByteArray): Result<Run, DataError.Network>

    suspend fun deleteRun(id: String): EmptyDataResult<DataError.Network>

}