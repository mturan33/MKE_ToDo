package com.example.mke_todo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var noteViewModel: NoteViewModel
    private var selectedDay: Int? = null // Seçilen gün

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewModel bağlantısı
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        val textViewMonth = findViewById<TextView>(R.id.textViewMonth)
        val recyclerViewCalendar = findViewById<RecyclerView>(R.id.recyclerViewCalendar)
        val editTextNote = findViewById<EditText>(R.id.editTextNote)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)

        // Şu anki ayı göster
        val calendar = Calendar.getInstance()
        val monthFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        textViewMonth.text = monthFormat.format(calendar.time)

        // Ayın günlerini oluştur
        val days = (1..calendar.getActualMaximum(Calendar.DAY_OF_MONTH)).toList()
        val notesMap = mutableMapOf<Int, List<String>>() // Günlere göre not listesi

        // RecyclerView setup
        calendarAdapter = CalendarAdapter(days, notesMap) { day ->
            selectedDay = day
            editTextNote.hint = "$day. gün için not ekleyin"
        }

        recyclerViewCalendar.layoutManager = GridLayoutManager(this, 7) // 7 sütunlu grid
        recyclerViewCalendar.adapter = calendarAdapter

        // Not ekleme butonu
        buttonAdd.setOnClickListener {
            val noteText = editTextNote.text.toString()
            selectedDay?.let { day ->
                if (noteText.isNotBlank()) {
                    val currentNotes = notesMap[day] ?: emptyList()
                    notesMap[day] = currentNotes + noteText
                    calendarAdapter.notifyDataSetChanged()
                    editTextNote.text.clear()
                }
            }
        }
    }
}
