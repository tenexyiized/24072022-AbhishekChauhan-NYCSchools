package com.example.baseforall.common.utils

import com.example.baseforall.common.result.Karma

interface SafeRequestExecutor {
     suspend fun <T:Any> executeRequest(apiCall:suspend() -> T): T
}