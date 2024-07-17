package com.example.api.data

import com.example.api.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RepositoryIm(
    private val api: ApiInterface
):Repository{
    override suspend fun getProductsList(): Flow<Result<List<Product>>> {
        return flow {
            val productsFromApi = try {
                api.getProductsList()

            }catch (e:IOException){
                e.printStackTrace()
                emit(Result.Error("Error Loading Products"))
            return@flow
            }
            catch (e:HttpException){
                e.printStackTrace()
                emit(Result.Error("Error Loading Products"))
                return@flow
            }
            catch (e:Exception){
                e.printStackTrace()
                emit(Result.Error("Error Loading Products"))
                return@flow
            }
            emit(Result.Success(productsFromApi))
        }
    }
}