package com.nackun.todo.presentation.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nackun.todo.R
import com.nackun.todo.databinding.ListItemBinding

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListItemViewHolder>() {

    private var toDoListModel: List<ListTodoModel> = listOf()
    private lateinit var toDoItemClickListener: (ListTodoModel) -> Unit
    private lateinit var toDoCheckListener: (ListTodoModel) -> Unit

    inner class ListItemViewHolder(
        private val binding: ListItemBinding,
        val toDoItemClickListener: (ListTodoModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: ListTodoModel) = with(binding) {
            listItemCheck.text = data.title
            checkToDoComplete(data.hasCompleted)
        }

        fun bindViews(data: ListTodoModel) = with(binding) {
            listItemCheck.setOnClickListener {
                toDoCheckListener(
                    data.copy(hasCompleted = listItemCheck.isChecked)
                )
                checkToDoComplete(listItemCheck.isChecked)
            }
            root.setOnClickListener {
                toDoItemClickListener(data)
            }
        }

        private fun checkToDoComplete(isChecked: Boolean) = with(binding) {
            listItemCheck.isChecked = isChecked
            listItemConstraint.setBackgroundColor(
                ContextCompat.getColor(
                    root.context,
                    if (isChecked) {
                        R.color.gray_300
                    } else {
                        R.color.white
                    }
                )
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(view, toDoItemClickListener)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bindData(toDoListModel[position])
        holder.bindViews(toDoListModel[position])
    }

    override fun getItemCount(): Int = toDoListModel.size

    @SuppressLint("NotifyDataSetChanged")
    fun setToDoList(
        toDoListModel: List<ListTodoModel>,
        toDoItemClickListener: (ListTodoModel) -> Unit,
        toDoCheckListener: (ListTodoModel) -> Unit
    ) {
        this.toDoListModel = toDoListModel
        this.toDoItemClickListener = toDoItemClickListener
        this.toDoCheckListener = toDoCheckListener
        notifyDataSetChanged()
    }
}
