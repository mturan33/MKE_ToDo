

package com.example.mke_todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalendarAdapter(
    private val days: List<Int>,  // Ayın günleri (1-30 gibi)
    private val notes: Map<Int, List<String>>, // Günlere eklenen notlar
    private val onDayClick: (Int) -> Unit // Gün tıklandığında işlem
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewDay: TextView = view.findViewById(R.id.textViewDay)
        val textViewNotes: TextView = view.findViewById(R.id.textViewNotes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.calendar_day_item, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val day = days[position]
        holder.textViewDay.text = day.toString()

        // O güne ait notları göster
        val dayNotes = notes[day]?.joinToString("\n") ?: ""
        holder.textViewNotes.text = dayNotes

        // Gün tıklandığında not eklemek için
        holder.itemView.setOnClickListener {
            onDayClick(day)
        }
    }

    override fun getItemCount(): Int = days.size
}
