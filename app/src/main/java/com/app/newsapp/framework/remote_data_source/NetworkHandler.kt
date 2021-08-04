package com.app.newsapp.framework.remote_data_source

import android.util.Log
import com.app.core.domain.util.APIState
import com.app.core.domain.util.AppExceptions
import com.app.core.domain.util.Constants
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class NetworkHandler<RESPONSE : Any> @Inject constructor(private val iODispatcher: CoroutineDispatcher) {

    private lateinit var state: APIState<RESPONSE>
    private lateinit var response: Response<RESPONSE>

    suspend fun callAPI(networkCall: suspend () -> Response<RESPONSE>) = flow {
        emit(APIState.LoadingState)
        val apiCall = CoroutineScope(iODispatcher)
            .launch(CoroutineExceptionHandler { _, exception ->
                Log.e(
                    "${Constants.LOGGER_KEY} :${NetworkHandler::class.java.simpleName}",
                    exception.toString()
                )
            }) {
                withTimeout(Constants.NETWORK_READ_TIME_OUT) {
                    delay(3000L)
                    response = networkCall()
                }
            }
        apiCall.join()
        apiCall.invokeOnCompletion {
            state = it?.let { error ->
                APIState.ErrorState(error)
            } ?: getDataOrThrowException(response)
        }
        emit(state)
    }.catch {
        emit(APIState.ErrorState(AppExceptions.GenericErrorException))
    }.flowOn(iODispatcher).distinctUntilChanged()

    private fun getDataOrThrowException(response: Response<RESPONSE>): APIState<RESPONSE> {
        return if (response.isSuccessful) {
            response.body()?.let {
                if (it.toString().isEmpty()) {
                    APIState.ErrorState(AppExceptions.EmptyResponseException)
                } else {
                    APIState.DataStat(it)
                }
            } ?: APIState.ErrorState(AppExceptions.NullResponseException)
        } else {
            APIState.ErrorState(AppExceptions.EmptyResponseException)
        }
    }
}
