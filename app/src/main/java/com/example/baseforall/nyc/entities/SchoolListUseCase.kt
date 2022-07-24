package com.example.baseforall.nyc.entities

import com.example.baseforall.common.abstractUsecases.UseCase
import com.example.baseforall.common.di.IoDispatcher
import com.example.baseforall.nyc.data.mappers.SchoolListRawAndPlainMapper
import com.example.baseforall.nyc.data.models.SchoolListPlainResponse
import com.example.baseforall.nyc.data.repositories.SchoolRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SchoolListUseCase @Inject constructor(
    val repository: SchoolRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
): UseCase<Unit, List<SchoolListPlainResponse>>(dispatcher){

    override suspend fun execute(parameters: Unit): List<SchoolListPlainResponse> {

        try{
            return repository.getSchoolList()
        }catch (e:Exception){
            throw e
        }
    }

}