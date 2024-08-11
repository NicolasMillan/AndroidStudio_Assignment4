package ca.georgiancollege.assignmentfour
/*
* File Name: ListDataViewHolder.kt
* Student: Nicolas Millan
* Student Number: 200533728
* App Description: The data view holder that binds the data to the view elements
* */
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.assignmentfour.databinding.ItemViewBinding

class ListDataViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    // Bind data to the view elements
    fun bind(todoItem: TodoItem) {
        binding.title.text = todoItem.name
        binding.date.text = todoItem.dueDate
        binding.switchCompleted.isChecked = todoItem.isCompleted
    }
}