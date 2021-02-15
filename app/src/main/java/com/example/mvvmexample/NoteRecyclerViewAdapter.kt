package com.example.mvvmexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmexample.Model.Note
import java.util.*
import kotlin.collections.ArrayList

class NoteRecyclerViewAdapter(private val context: Context,private  val listener: INotesRvAdapater): RecyclerView.Adapter<NoteRecyclerViewAdapter.NoteViewHolder>() {
    val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val textView = itemView.findViewById<TextView>(R.id.text);
        val deleteButton = itemView.findViewById<AppCompatImageView>(R.id.deletebutton);


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val viewHolder  = NoteViewHolder( LayoutInflater.from(context).inflate(R.layout.item_note ,parent,false));
        viewHolder.deleteButton.setOnClickListener {
        listener.onItemClick(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text


    }

    fun updateList(newList:List<Note>)
    {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return allNotes.size

    }
}
interface INotesRvAdapater{
    fun onItemClick(note: Note)
}