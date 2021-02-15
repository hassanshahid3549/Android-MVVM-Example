package com.example.mvvmexample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmexample.Model.Note
import com.example.mvvmexample.room.dao.NotesDao
import java.security.AccessControlContext


@Database(entities =  arrayOf(Note::class),version = 1,exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract  fun getNoteDao():NotesDao

    companion object {
        @Volatile
        private var INSTANCE:NoteDatabase? = null

        fun getDatabase(context: Context):NoteDatabase{
            //If the instance is not null then return it.
            //If it is, then create the database.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,NoteDatabase::class.java,"note_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}