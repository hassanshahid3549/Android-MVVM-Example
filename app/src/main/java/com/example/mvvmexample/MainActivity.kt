package com.example.mvvmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmexample.Model.Note

class MainActivity : AppCompatActivity(),INotesRvAdapater {
    lateinit var   viewModel: NoteViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclverview = findViewById<RecyclerView>(R.id.recyclerviewNote)
        recyclverview.layoutManager = LinearLayoutManager(this)
        val adapater  = NoteRecyclerViewAdapter(this,this)
        recyclverview.adapter = adapater
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer {List ->
            List?.let {
                adapater.updateList(it)

            }
        })

    }

    override fun onItemClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val inputDataEdtxt = findViewById<EditText>(R.id.input)
        val noteText = inputDataEdtxt.text.toString()
        if (noteText.isNotEmpty())
        {
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this,"${noteText} Insterted",Toast.LENGTH_LONG).show()

        }
    }
}