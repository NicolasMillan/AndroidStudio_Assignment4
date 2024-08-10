package ca.georgiancollege.assignmentfour

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class DataManager private constructor()
{
    private val db: FirebaseFirestore = Firebase.firestore

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
    // insert a TVShow into the database
    suspend fun insert(listData: ListData) {
        try {
            db.collection("tvShows").document(listData.id).set(listData).await()
        }
        catch (e: Exception) {
            Log.e(TAG, "Error inserting TVShow: ${e.message}", e)
        }
    }

    // update a TVShow in the database
    suspend fun update(listData: ListData) {
        try {
            db.collection("tvShows").document(listData.id).set(listData).await()
        }
        catch (e: Exception) {
            Log.e(TAG, "Error updating TVShow: ${e.message}", e)
        }
    }

    // delete a TVShow from the database
    suspend fun delete(listData: ListData) {
        try {
            db.collection("tvShows").document(listData.id).delete().await()
        }
        catch (e: Exception) {
            Log.e(TAG, "Error deleting TVShow: ${e.message}", e)
        }
    }

    // get all TVShows from the database
    suspend fun getAllTVShows(): List<ListData> {
        return try {
            val result = db.collection("tvShows").get().await()
            result?.toObjects(ListData::class.java) ?: emptyList()
        } catch (e: Exception) {
            Log.e(TAG, "Error getting all TVShows: ${e.message}", e)
            emptyList()
        }
    }

    // get a TVShow by ID from the database
    suspend fun getTVShowById(id: String) : ListData? {
        return try {
            val result = db.collection("tvShows").document(id).get().await()
            result?.toObject(ListData::class.java)
        }
        catch (e: Exception) {
            Log.e(TAG, "Error getting TVShow by ID: ${e.message}", e)
            null
        }
    }
}