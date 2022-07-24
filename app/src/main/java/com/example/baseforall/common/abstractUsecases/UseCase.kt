package com.example.baseforall.common.abstractUsecases

import com.example.baseforall.common.result.Karma
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class UseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): Karma<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Karma.Success(it)
                }
            }
        } catch (e: Exception) {
            Karma.Error(e)
        }
    }

    /**
     * Override this to set the code to be executed.
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}