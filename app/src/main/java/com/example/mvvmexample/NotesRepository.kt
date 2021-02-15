package com.example.mvvmexample

import androidx.lifecycle.LiveData
import com.example.mvvmexample.Model.Note
import com.example.mvvmexample.room.dao.NotesDao

class NotesRepository(private val notesDao: NotesDao) {
    val allNotes:LiveData<List<Note>> = notesDao.getAllNotes()
    suspend fun insert(note: Note)
    {
        notesDao.insert(note);
    }

    suspend fun delete(note: Note)
    {
        notesDao.delete(note);
    }
}