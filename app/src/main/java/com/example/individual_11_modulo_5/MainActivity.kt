package com.example.individual_11_modulo_5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), EditWordFragment.OnWordEditedListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    private val words = mutableListOf<String>()
    private lateinit var adapter: WordAdapter
    private var selectedPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)
        fab = findViewById(R.id.fab)


        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WordAdapter(words) { position -> onWordClicked(position) }
        recyclerView.adapter = adapter


        fab.setOnClickListener {
            val newWord = "Palabra ${words.size + 1}"
            words.add(newWord)
            adapter.notifyItemInserted(words.size - 1)
        }
    }

    private fun onWordClicked(position: Int) {
        selectedPosition = position
        val clickedWord = words[position]
        val editWordFragment = EditWordFragment.newInstance(clickedWord)
        editWordFragment.show(supportFragmentManager, "editWordFragment")
    }


    override fun onWordEdited(newWord: String) {
        if (selectedPosition >= 0) {
            words[selectedPosition] = newWord
            adapter.notifyItemChanged(selectedPosition)
        }
    }
}
