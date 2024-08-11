package ca.georgiancollege.assignmentfour

/*
* File Name: ListDataAdapter.kt
* Student: Nicolas Millan
* Student Number: 200533728
* App Description: The data adapter for the recycler view
* */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ca.georgiancollege.assignmentfour.databinding.ItemViewBinding

class ListDataAdapter(private val onItemClicked: (TodoItem) -> Unit) :
        ListAdapter<TodoItem, ListDataViewHolder>(ListComparator()){
        // Create the view holder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDataViewHolder {
                val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ListDataViewHolder(binding)
        }
        // Bind data to the view holder
        override fun onBindViewHolder(holder: ListDataViewHolder, position: Int) {
                val todoItem = getItem(position)
                holder.bind(todoItem)
                holder.itemView.setOnClickListener {
                        onItemClicked(todoItem)
                }
        }

}
