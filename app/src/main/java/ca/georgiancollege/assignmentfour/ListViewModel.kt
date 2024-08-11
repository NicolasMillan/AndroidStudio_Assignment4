package ca.georgiancollege.assignmentfour

/*
* File Name: ListViewModel.kt
* Student: Nicolas Millan
* Student Number: 200533728
* App Description: ViewModel for the ListActivity
* */
import android.widget.CalendarView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Calendar

class ListViewModel : ViewModel() {

    // Create an alias for the DataManager singleton
    private val dataManager = DataManager.instance()

    // LiveData to hold the lists
    private val _dataLists = MutableLiveData<List<TodoItem>>()
    val dataLists: LiveData<List<TodoItem>> get() = _dataLists

    // LiveData to hold the selected lists
    private val _selectedList = MutableLiveData<TodoItem?>()
    val selectedData: LiveData<TodoItem?> get() = _selectedList

    // Function to save data
    fun saveData(todoItem: TodoItem) {
        viewModelScope.launch {
            if (todoItem.id.isEmpty()) {
                dataManager.saveData(todoItem)
            } else {
                dataManager.insertData(todoItem)
            }
            fetchData()
        }
    }

    // Function to get the selected date from the CalendarView
    fun getSelectedDate(calendarView: CalendarView): String {
        val calendar = Calendar.getInstance()
        calendarView.date.let {
            calendar.timeInMillis = it
        }
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1 // Months are zero-based
        val year = calendar.get(Calendar.YEAR)

        return "$dayOfMonth/$month/$year"
    }

    // Function to fetch data
    fun fetchData() {
        viewModelScope.launch {
            _dataLists.value = dataManager.fetchData()
        }
    }

    // Function to delete data
    fun deleteData(todoItem: TodoItem) {
        viewModelScope.launch {
            dataManager.deleteData(todoItem)
            fetchData()
        }
    }

    // Function to load data by ID
    fun loadDataById(id: String) {
        viewModelScope.launch {
            _selectedList.value = dataManager.loadDataById(id)
        }
    }
}
