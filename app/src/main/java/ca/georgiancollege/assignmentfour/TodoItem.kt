package ca.georgiancollege.assignmentfour

/*
* File Name: todoItem.kt
* Student: Nicolas Millan
* Student Number: 200533728
* App Description:  class that sets the standards for the data
* */
import com.google.firebase.firestore.DocumentId

data class TodoItem(
    @DocumentId val id: String = "",
    val name: String = "",
    val notes: String = "",
    val isCompleted: Boolean = false,
    val dueDate: String = ""
)
{
    constructor() : this("", "", "", false, "")
}