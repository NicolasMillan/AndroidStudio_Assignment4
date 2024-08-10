package ca.georgiancollege.assignmentfour

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class ListData(
    @DocumentId val id: String = "",
    val name: String,
    val date: String,
    val completed: Boolean
)
{
    // No-argument constructor required for Firestore deserialization
    constructor() : this("", "", "", false)
}