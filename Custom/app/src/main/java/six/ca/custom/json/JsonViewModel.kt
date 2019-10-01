package six.ca.custom.json

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @author hellenxu
 * @date 2019-09-29
 * Copyright 2019 Six. All rights reserved.
 */
class JsonViewModel: ViewModel() {
    private val _favNumSet = MutableLiveData<Array<FavResponse>>()
    val favNumSet: LiveData<Array<FavResponse>>
        get() = _favNumSet
    private val serviceApi = NetClient.getInstance().create(FavApi::class.java)

    fun getFavNums() {
        viewModelScope.launch {
            val result = serviceApi.fetchFavNums()

            if (result.isNotEmpty()) {
                _favNumSet.postValue(result)
            } else {
                _favNumSet.postValue(emptyArray())
            }
        }
    }
}