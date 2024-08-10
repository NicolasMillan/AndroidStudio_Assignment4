package ca.georgiancollege.assignmentfour

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.collections.List

class ListViewModel : ViewModel()
{
    // create an alias for the DataManager singleton
    private val dataManager = DataManager.instance()

    // LiveData to hold the list of TVShows
    private val m_lists = MutableLiveData<List<ListData>>()
    val tvShows: LiveData<List<ListData>> get() = m_lists

    // LiveData to hold the selected TVShow
    private val m_list = MutableLiveData<ListData?>()
    val listData: LiveData<ListData?> get() = m_list

    // Function to load all TVShows from the DataManager
    fun loadAllLists() {
        viewModelScope.launch {
            m_lists.value = dataManager.getAllTVShows()
        }
    }

    // Function to load a specific TVShow by ID from the DataManager
    fun loadListById(id: String) {
        viewModelScope.launch {
            m_list.value = dataManager.getTVShowById(id)
        }
    }

    // Function to save or update a TVShow in the DataManager
    fun saveList(listData: ListData) {
        viewModelScope.launch {
            if (listData.id.isEmpty()) {
                dataManager.insert(listData)
            } else {
                dataManager.update(listData)
            }
            loadAllLists()
        }
    }

    // Function to delete a List Data from the DataManager
    fun deleteList(listData: ListData) {
        viewModelScope.launch {
            dataManager.delete(listData)
            loadAllLists()
        }
    }
}