package com.odev.mobilodev2
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class ToDoAdapter(private val toDoList: MutableList<ToDoItem>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val checkBoxDone: CheckBox = itemView.findViewById(R.id.checkBoxDone)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val currentItem = toDoList[position]
        holder.textViewTitle.text = currentItem.title
        holder.checkBoxDone.isChecked = currentItem.isDone

        holder.checkBoxDone.setOnCheckedChangeListener { buttonView, isChecked ->

            if (currentItem.isDone != isChecked) {
                currentItem.isDone = isChecked
                if (isChecked) {
                    removeItem(position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    private fun removeItem(position: Int) {
        toDoList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(toDoItem: ToDoItem) {
        toDoList.add(toDoItem)
        notifyItemInserted(toDoList.size - 1)
    }

    fun setData(toDoList: List<ToDoItem>) {
        this.toDoList.clear()
        this.toDoList.addAll(toDoList)
        notifyDataSetChanged()
    }
}