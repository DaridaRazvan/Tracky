package com.app.tracky

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tracky.data.TodoElement
import kotlinx.android.synthetic.main.todo.view.*

class ListAdapter(private var clickListener: ClickListener): RecyclerView.Adapter<ListAdapter.TodoViewHolder>() {

    private var todoList = emptyList<TodoElement>()

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.todo,parent,false))
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentItem = todoList[position]

        holder.itemView.apply {
            tw_todoName.text = currentItem.name
            cb_is_checked.isChecked = currentItem.isChecked

            //tw_todoName.showStrikeThrough(currentItem.isChecked)
            //cl_item.applyBackgroundForItem(currentItem.isChecked)

            cb_is_checked.setOnCheckedChangeListener{ _, isChecked ->
                clickListener.onTodoClick(currentItem, isChecked)
                Log.v("onCheckedChangeListener", "item clicked")
            }
        }
    }

    fun setData(todos: List<TodoElement>){
        this.todoList = todos
        //notifyDataSetChanged()
    }

    fun getTodoAtPosition(position : Int): TodoElement{
        return this.todoList[position]
    }

    interface ClickListener{
        fun onTodoClick(todo: TodoElement, isChecked: Boolean)
    }
}