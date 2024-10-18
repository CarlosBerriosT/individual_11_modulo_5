
package com.example.individual_11_modulo_5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WordAdapter(private val words: List<String>, private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<WordAdapter.WordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val word = words[position]
        holder.bind(word, clickListener)
    }

    override fun getItemCount(): Int {
        return words.size
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val wordTextView: TextView = itemView.findViewById(R.id.textViewWord)

        fun bind(word: String, clickListener: (Int) -> Unit) {
            wordTextView.text = word
            itemView.setOnClickListener {
                clickListener(adapterPosition)  // Aquí se pasa `adapterPosition`, que es un `Int`.
            }
        }
    }
}

