package com.odev.mobilodev2
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog



class MainActivity : AppCompatActivity() {

    private val toDoList = mutableListOf(
        ToDoItem(1, "Task 1", false),
        ToDoItem(2, "Task 2", true),
        ToDoItem(3, "Task 3", false)
    )

    private val adapter: ToDoAdapter by lazy { ToDoAdapter(toDoList) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter.setData(toDoList)

        fab.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun showAddTaskDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("YENİ GÖREV")

        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("EKLE") { dialog, which ->
            val newTaskTitle = input.text.toString()
            if (newTaskTitle.isNotEmpty()) {
                val newItem = ToDoItem(toDoList.size + 1, newTaskTitle, false)
                adapter.addItem(newItem)
            }
        }
        builder.setNegativeButton("GERİ") { dialog, which -> dialog.cancel() }

        builder.show()
    }
}