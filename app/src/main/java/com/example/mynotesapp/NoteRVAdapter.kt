package com.example.mynotesapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(private val context: Context, private val listener:INotesRVAdapter): RecyclerView.Adapter<NoteRVAdapter.NoteViewHolder>() {
    private val allNotes=ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
       val viewHolder= NoteViewHolder( LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))

        return  viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currNote= allNotes[position]
        holder.textView.text= currNote.text
        holder.deleteBtn.setOnClickListener{
            listener.onItemClicked(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
        for(item in newList)
        Log.d("ggwp",item.text)
    }

    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteBtn= itemView.findViewById<ImageView>(R.id.delete)

    }
}

interface INotesRVAdapter{
    fun onItemClicked(note:Note)
}