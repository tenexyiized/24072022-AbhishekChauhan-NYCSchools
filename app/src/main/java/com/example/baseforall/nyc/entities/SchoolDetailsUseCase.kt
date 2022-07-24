package com.example.baseforall.nyc.entities

import com.example.baseforall.common.abstractUsecases.UseCase
import com.example.baseforall.common.di.IoDispatcher
import com.example.baseforall.nyc.data.models.SchoolDetailPlainResponse
import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import com.example.baseforall.nyc.data.repositories.SchoolRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolDetailsUseCase @Inject constructor(
    val repository: SchoolRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<String, List<SchoolDetailPlainResponse>>(dispatcher){

    override suspend fun execute(parameters: String): List<SchoolDetailPlainResponse> {

        try{
            return repository.getSchoolDetails(parameters)
        }catch (e:Exception){
            throw e
        }
    }

}