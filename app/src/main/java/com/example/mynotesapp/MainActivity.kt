package com.example.mynotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.RVNote)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteRVAdapter(this, this)
        recyclerView.adapter = adapter
        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this, { list ->
            list?.let {
                adapter.updateList(list)
            }
        })


    }

    override fun onItemClicked(note: Note) {
        noteViewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitData(view: android.view.View) {

        var edt=findViewById<EditText>(R.id.input)
        var noteText =edt.text.toString()
            if (noteText.isEmpty())
            Toast.makeText(this, "Please Enter Text", Toast.LENGTH_SHORT).show()
        else {
            noteViewModel.insertNote(Note(noteText))
            Toast.makeText(this, "$noteText Added", Toast.LENGTH_SHORT).show()
            edt.text.clear()
        }


    }
}