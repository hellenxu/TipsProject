package six.ca.droiddailyproject.fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author hellenxu
 * @date 2020-03-15
 * Copyright 2020 Six. All rights reserved.
 */
class LevelOneVm: ViewModel() {
    private val _data = SingleLiveEvent<Int>()
    internal val data: SingleLiveEvent<Int>
    get() = _data

    fun fetchData() {
        viewModelScope.launch {
            _data.setValue(call())
        }
    }

    private suspend fun call(): Int = withContext(Dispatchers.IO) {
        Thread.sleep(5000)
        return@withContext 10
    }
}