package com.example.mvvmexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmexample.Model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NoteViewModel (application: Application):AndroidViewModel(application)
{
    val allNotes:LiveData<List<Note>>
    private val repository :NotesRepository
    init {

        val database = NoteDatabase.getDatabase(application).getNoteDao()
         repository = NotesRepository(database)
        allNotes = repository.allNotes
    }

    fun deleteNote(note:Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Note)= viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}