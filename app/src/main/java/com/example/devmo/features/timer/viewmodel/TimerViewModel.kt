package com.example.devmo.features.timer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


data class Revision(val startTime: String, val duration: Long, val subject: String)

class TimerViewModel : ViewModel() {
    private val _timer = MutableStateFlow(0L)
    val timer = _timer.asStateFlow()

    private var timerJob: Job? = null

    private val _revisions = MutableStateFlow<List<Revision>>(emptyList())
    val revisions = _revisions.asStateFlow()

    var selectedSubject: String = ""

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
        val revision = Revision(startTime, duration, selectedSubject)

        // Ajouter la révision à la liste
        _revisions.value = _revisions.value + revision

        _timer.value = 0
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }

    fun convertStringToLong(dateString: String): Long {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date: Date? = format.parse(dateString) // Analyse la chaîne en Date
        return date?.time ?: 0L // Retourne le timestamp en millisecondes ou 0 si la date est nulle
    }

    // Méthodes pour calculer les statistiques
    fun getTotalHours(): String {
        if ((_revisions.value.sumOf { it.duration } / 3600).toInt() == 0) {
            if ((_revisions.value.sumOf { it.duration } / 60).toInt() == 0) {
                val total: Long = _revisions.value.sumOf { it.duration }
                return "$total secondes"
            }else{
                val total: Long = _revisions.value.sumOf { it.duration } /60
                return "$total minutes"
            }
        } else {
            val total: Long = _revisions.value.sumOf { it.duration } / 3600
            return  "$total heures"
        }
    }

    fun getWeeklyHours(): String {
        val currentWeek = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)
        val revisions_week : List<Revision> = _revisions.value.filter {
            val revisionWeek = Calendar.getInstance().apply {
                timeInMillis = convertStringToLong(it.startTime)
            }.get(Calendar.WEEK_OF_YEAR)
            revisionWeek == currentWeek
        }
        if ((revisions_week.sumOf { it.duration } / 3600).toInt() == 0) {
            if ((revisions_week.sumOf { it.duration } / 60).toInt() == 0) {
                val total: Long = revisions_week.sumOf { it.duration }
                return "$total secondes"
            }else{
                val total: Long = revisions_week.sumOf { it.duration } /60
                return "$total minutes"
            }
        } else {
            val total: Long = revisions_week.sumOf { it.duration } / 3600
            return  "$total heures"
        }
    }


    fun getMostRevisedSubject(): String {
        return _revisions.value.groupingBy { it.subject }
            .eachCount()
            .maxByOrNull { it.value }
            ?.key ?: "Aucune matière révisée"
    }

    fun getLeastRevisedSubject(): String {
        return _revisions.value.groupingBy { it.subject }
            .eachCount()
            .minByOrNull { it.value }
            ?.key ?: "Aucune matière révisée"
    }
}

