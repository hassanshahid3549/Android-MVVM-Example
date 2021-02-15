package com.example.mvvmexample.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmexample.Model.Note

@Dao
interface NotesDao {

    @Insert(
        onConflict = OnConflictStrategy.IGNORE
    )


    //Insert Delete run on Background thread.
    suspend fun insert(note: Note);
    @Delete
    suspend fun delete(note:Note);
    @Query("SELECT * FROM notes_table order by id ASC")
        fun getAllNotes():LiveData<List<Note>>

}