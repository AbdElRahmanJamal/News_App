package com.app.newsapp.presentation.breaking_news.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.core.domain.entities.breaking_news_entity.BreakingNewsDomainModelList
import com.app.core.domain.interactors.breaking_news_interactors.GetBreakingNewsByCountryAndPageNumberUseCase
import com.app.core.domain.util.APIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreakingNewsViewModel
@Inject constructor(
    private val getBreakingNewsByCountryAndPageNumber: GetBreakingNewsByCountryAndPageNumberUseCase
) : ViewModel() {

    private val aPIState = MutableLiveData<APIState<BreakingNewsDomainModelList>>()
    var _aPIState = MutableLiveData<APIState<BreakingNewsDomainModelList>>()

    fun getBreakingNewsByCountryAndPageNumber(countryCode: String, pageNumber: Int) {
        _aPIState = aPIState
        viewModelScope.launch(Dispatchers.IO) {
            getBreakingNewsByCountryAndPageNumber.getBreakingNewsByCountryAndPageNumber(
                countryCode,
                pageNumber
            )
                .collect { flowState ->
                    aPIState.postValue(flowState)
                }
        }
    }

}