package com.example.baseforall.common.utils

import com.example.baseforall.common.result.Karma
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SafeRequestExecutorImpl @Inject constructor(): SafeRequestExecutor {
    override suspend fun <T:Any> executeRequest(apiCall:suspend() -> T): T{
        try{
            var t:T = apiCall()
            return t
        }
        catch (throwable:Throwable){
            throw throwable
        }
    }
}