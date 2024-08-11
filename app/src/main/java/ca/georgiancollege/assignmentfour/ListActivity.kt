package ca.georgiancollege.assignmentfour

/*
* File Name: ListActivity.kt
* Student: Nicolas Millan
* Student Number: 200533728
* App Description: Display the list of activities from Firestore
* */
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignmentfour.databinding.TodoListActivityBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: TodoListActivityBinding
    private val viewModel: ListViewModel by viewModels()
    private lateinit var dataManager: DataManager
    private val TAG = "DataManager"

    // Initialize the adapter to pass data to the RecyclerView
    private val adapter = ListDataAdapter { todoItem: TodoItem ->
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("todoItemId", todoItem.id)
        }
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize DataManager
        dataManager = DataManager.instance()

        binding.firstRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.firstRecyclerView.adapter = adapter

        // Fetch data from Firestore
        viewModel.fetchData()

        // Observe dataLists LiveData from the ViewModel
        viewModel.dataLists.observe(this) { todoItems ->
            adapter.submitList(todoItems)
            logTodoItems(todoItems)
        }

        // on click listener for the form button
        binding.formBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // Log the fetched data testing
    private fun logTodoItems(todoItems: List<TodoItem>) {
        if (todoItems.isEmpty()) {
            Log.d(TAG, "No documents found")
        } else {
            for (todoItem in todoItems) {
                Log.d(TAG, "Name: ${todoItem.name}")
                Log.d(TAG, "Notes: ${todoItem.notes}")
                Log.d(TAG, "Completed: ${todoItem.isCompleted}")
                Log.d(TAG, "Due Date: ${todoItem.dueDate}")
            }
        }
    }
}
