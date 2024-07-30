package com.ssk.auth.data.data

import android.util.Patterns
import com.ssk.auth.domain.domain.PatternValidator

object EmailPatternValidator: PatternValidator {
    override fun matches(value: String): Boolean {
       return Patterns.EMAIL_ADDRESS.matcher(value).matches()
    }
}