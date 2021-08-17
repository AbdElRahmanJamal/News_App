package com.app.newsapp.presentation.breaking_news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.domain.entities.breaking_news_entity.BreakingNewsDomainModelList
import com.app.core.domain.interactors.breaking_news_interactors.GetBreakingNewsByCountryAndPageNumberUseCase
import com.app.core.domain.util.APIState
import com.app.core.domain.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreakingNewsViewModel
@Inject constructor(
    private val getBreakingNewsByCountryAndPageNumber: GetBreakingNewsByCountryAndPageNumberUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _aPIState = MutableLiveData<APIState<BreakingNewsDomainModelList>>()
    var aPIState = MutableLiveData<APIState<BreakingNewsDomainModelList>>()

    suspend fun getBreakingNewsByCountryAndPageNumber(countryCode: String, pageNumber: Int) {
        aPIState = _aPIState
        viewModelScope.launch(dispatcherProvider.io) {
            getBreakingNewsByCountryAndPageNumber.getBreakingNewsByCountryAndPageNumber(
                countryCode,
                pageNumber
            ).collect { flowState ->
                aPIState.postValue(flowState)
            }
        }
    }

}