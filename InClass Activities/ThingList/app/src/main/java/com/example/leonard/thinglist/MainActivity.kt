package com.example.leonard.thinglist

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.LinkedList

class MainActivity : AppCompatActivity() {
    private val thingList : MutableList<String> = LinkedList<String>()
    private lateinit var recyclerView : RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..40) {
            thingList.add("Thing $i")
        }

        val thingListAdapter = ThingListAdapter(thingList)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.adapter = thingListAdapter

        floatingActionButton = findViewById(R.id.fab)
        floatingActionButton.setOnClickListener{
            val next = thingList.size
            thingList.add("Thing $next")
            thingListAdapter.notifyItemInserted(next)
            recyclerView.smoothScrollToPosition(next)
        }
    }
}