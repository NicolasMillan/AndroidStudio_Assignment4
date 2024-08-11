package ca.georgiancollege.assignmentfour
/*
* File Name: DataManager.kt
* Student: Nicolas Millan
* Student Number: 200533728
* App Description: DataManager
* */

import android.content.ContentValues
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class DataManager private constructor()
{
    private val db: FirebaseFirestore = Firebase.firestore

    // Singleton instance
    companion object {
        private val TAG = "DataManager"
        @Volatile
        private var m_instance: DataManager? = null
        fun instance(): DataManager
        {
            if(m_instance == null)
            {
                synchronized(this) {
                    if(m_instance == null) {
                        m_instance = DataManager()
                    }
                }
            }
            return m_instance!!
        }
    }

    // Add a new document with a generated ID
    suspend fun saveData (todoItem: TodoItem){
        try {
            // Add a new document with a generated ID
            db.collection("listDataBase")
                .add(todoItem)
                .addOnSuccessListener { documentReference ->
                    Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }
        }catch (e: Exception) {
            Log.e(TAG, "Error updating Data: ${e.message}", e)
        }
    }

    // Add a new document with a generated ID
    suspend fun insertData (todoItem: TodoItem){
        try {
            // Add a new document with a generated ID
            db.collection("listDataBase")
                .add(todoItem)
                .addOnSuccessListener { documentReference ->
                    Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(ContentValues.TAG, "Error adding document", e)
                }
        }catch (e: Exception) {
            Log.e(TAG, "Error updating Data: ${e.message}", e)
        }
    }

    // Update an existing document
    suspend fun fetchData(): List<TodoItem> {
        return try {
            val snapshot = db.collection("listDataBase").get().await()
            snapshot?.toObjects(TodoItem::class.java) ?: emptyList()
//            snapshot.documents.mapNotNull { document -> document.toObject(TodoItem::class.java) }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching data: ${e.message}", e)
            emptyList()
        }
    }

    // Delete a document by its ID Sample 2
    suspend fun deleteData2(todoItem: TodoItem) {
        try {
            db.collection("listDataBase").document(todoItem.id).delete().await()
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting data: ${e.message}", e)
        }
    }

    // Delete a document by its ID
    suspend fun deleteData(todoItem: TodoItem) {
        try {
            db.collection("listDataBase").document(todoItem.id).delete().await()
            Log.d(TAG, "DocumentSnapshot successfully deleted!")
        } catch (e: Exception) {
            Log.e(TAG, "Error deleting data: ${e.message}", e)
        }
    }

    // Update an existing document
    suspend fun loadDataById(id: String): TodoItem? {
        return try {
            val documentSnapshot = db.collection("listDataBase").document(id).get().await()
            documentSnapshot?.toObject(TodoItem::class.java)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading data by ID: ${e.message}", e)
            null
        }
    }
}
