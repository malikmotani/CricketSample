package com.sapient.cricketsample.ui.match

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sapient.cricketsample.data.model.MatchItem
import com.sapient.cricketsample.data.model.NetworkCallResult
import com.sapient.cricketsample.domain.repository.CricketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CricketMatchViewModel @Inject constructor(
    private val cricketRepository: CricketRepository,
) : ViewModel() {

    private val _matchLiveData = MutableLiveData<NetworkCallResult<List<MatchItem>>>()
    val matchLiveData: LiveData<NetworkCallResult<List<MatchItem>>> = _matchLiveData

    private val _matchDetailLiveData = MutableLiveData<MatchItem>()
    val matchDetailLiveData: LiveData<MatchItem> = _matchDetailLiveData

    fun fetchMatches() {
        viewModelScope.launch {
            val response = cricketRepository.getMatches()
            _matchLiveData.postValue(response)
        }
    }

    fun fetchMatchById(matchId: String) {
        viewModelScope.launch {
            val list = matchLiveData.value
            if (list is NetworkCallResult.Success<List<MatchItem>>) {
                list.value.find {
                    it.id == matchId
                }?.let {
                    _matchDetailLiveData.value = it
                }
            }
        }
    }
}