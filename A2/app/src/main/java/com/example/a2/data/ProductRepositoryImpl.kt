package com.example.a2.data

import com.example.a2.data.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ProductRepositoryImpl(
    private val api: Api
) : ProductsRepository {
    override suspend fun getProductsList(): Flow<Result<List<Product>>> {
        return flow {
            val productsFromApi = try {
                api.getProductsList()

            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading Data"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading Data"))
                return@flow

            } catch (e: Exception) {
                e.printStackTrace()
                emit(Result.Error(message = "Error Loading Data"))
                return@flow

            }
            emit(Result.Success(productsFromApi.products))

        }
    }

}