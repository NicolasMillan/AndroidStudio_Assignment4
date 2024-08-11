package ca.georgiancollege.assignmentfour

/*
* File Name: ListComparator.kt
* Student: Nicolas Millan
* Student Number: 200533728
* App Description: Compares items in the list
* */

import androidx.recyclerview.widget.DiffUtil

class ListComparator: DiffUtil.ItemCallback<TodoItem>() {
    // Compare items based on their IDs
    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.id == newItem.id
    }
    // Compare contents of items
    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem == newItem
    }
}