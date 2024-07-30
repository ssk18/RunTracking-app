package com.ssk.auth.domain.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}