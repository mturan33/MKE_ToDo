package com.example.mke_todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {
    private val _notes = MutableLiveData<List<Note>>(emptyList())
    val notes: LiveData<List<Note>> get() = _notes

    fun addNote(noteText: String) {
        if (noteText.isNotBlank()) {
            val currentList = _notes.value ?: emptyList()
            _notes.value = currentList + Note(noteText)
        }
    }
}
