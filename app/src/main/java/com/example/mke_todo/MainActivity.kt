package com.example.mke_todo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by viewModels()
    private lateinit var noteAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewNotes = findViewById<RecyclerView>(R.id.recyclerViewNotes)
        val editTextNote = findViewById<EditText>(R.id.editTextNote)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)

        noteAdapter = NoteAdapter(emptyList())
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        recyclerViewNotes.adapter = noteAdapter

        buttonAdd.setOnClickListener {
            val noteText = editTextNote.text.toString()
            noteViewModel.addNote(noteText)
            editTextNote.text.clear()
        }

        noteViewModel.notes.observe(this) { notes ->
            noteAdapter.updateNotes(notes)
        }
    }
}
