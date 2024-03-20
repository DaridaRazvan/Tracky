package com.app.tracky

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.tracky.data.TodoElement
import com.app.tracky.data.TodoViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment(), ListAdapter.ClickListener {

    private lateinit var mTodoViewModel: TodoViewModel
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_list, container, false)
        //Recyclerview
        adapter = ListAdapter(this)
        val recyclerView = view.rvMainList
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(recyclerView)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mTodoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        mTodoViewModel.todos.observe(viewLifecycleOwner) { todo ->
            adapter.setData(todo)
        }

//        view.bAdd.setOnClickListener {
//            findNavController().navigate(R.id.action_listFragment_to_addFragment)
//        }

        return view
    }

    override fun onTodoClick(todo: TodoElement, isChecked: Boolean) {
        Toast.makeText(context,"ToDo done, Good Job!", Toast.LENGTH_LONG).show()
        todo.isChecked = isChecked
        mTodoViewModel.updateTodo(todo)
    }

    private val itemTouchHelperCallback =
        object :
            ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val todo: TodoElement = adapter.getTodoAtPosition(position)
                mTodoViewModel.deleteTodo(todo)
            }
        }
}