package com.example.devmo.features.timer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Revision(val startTime: String, val duration: Long)

class TimerViewModel : ViewModel() {
    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private var timerJob: Job? = null

    private val _revisions = MutableStateFlow<List<Revision>>(emptyList())
    val revisions = _revisions.asStateFlow()

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                delay(1000)
                _timer.value++
            }
        }
    }

    fun pauseTimer() {
        timerJob?.cancel()
    }

    fun stopTimer() {

        val duration = _timer.value
        val startTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val revision = Revision(startTime, duration)

        // Ajouter la révision à la liste
        _revisions.value = _revisions.value + revision

        _timer.value = 0
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
