package com.example.devmo.features.revision

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Revision(val startTime: String, val duration: Long, val subject: String)

class RevisionViewModel : ViewModel() {
    private val _revisions = MutableLiveData<List<Revision>>(emptyList())
    val revisions: LiveData<List<Revision>> get() = _revisions

    fun addRevision(revision: Revision) {
        val currentList = _revisions.value ?: emptyList()
        _revisions.value = currentList + revision
    }
}