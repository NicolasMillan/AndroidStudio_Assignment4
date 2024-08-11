package ca.georgiancollege.assignmentfour

/*
* File Name: MainActivity.kt
* Student: Nicolas Millan
* Student Number: 200533728
* App Description: The activity that runs the project
* */

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.assignmentfour.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ListViewModel by viewModels()
    private lateinit var dataManager: DataManager
    private var todoItemId: String? = null
    private val TAG = "DataManager"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize DataManager
        dataManager = DataManager.instance()
        // Get the todoItemId from the intent
        todoItemId = intent.getStringExtra("todoItemId")
        Log.d(TAG, "todoItemId: $todoItemId")
        if(todoItemId != null){
            viewModel.loadDataById(todoItemId!!)
        }else{
            binding.buttonDelete.visibility = View.GONE
        }
        // Observe selectedData LiveData from the ViewModel
        viewModel.selectedData.observe(this) { todoItem ->
            todoItem?.let {
                binding.editTextName.setText(it.name)
                binding.editTextNotes.setText(it.notes)
                binding.switchCompleted.isChecked = it.isCompleted
                binding.calendarView.date = it.dueDate.toLong()
            }
        }

        binding.buttonSave.setOnClickListener {
            saveUserData()
        }
        binding.buttonDelete.setOnClickListener {
            deleteData()
        }
        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }

    // Save the user data to Firestore
    private fun saveUserData(){
        val name = binding.editTextName.text.toString().trim()
        val notes = binding.editTextNotes.text.toString().trim()
        val isCompleted = binding.switchCompleted.isChecked
        val selectedDate = viewModel.getSelectedDate(binding.calendarView)

        if (name.isNotEmpty() && notes.isNotEmpty()) {
            val todoItem = TodoItem(id = todoItemId ?: "", name = name, notes = notes, isCompleted = isCompleted, dueDate = selectedDate)
            viewModel.saveData(todoItem)
            Toast.makeText(this, "Document Saved", Toast.LENGTH_SHORT).show()
            finish()
        } else{
            Toast.makeText(this, "Please enter a name and notes", Toast.LENGTH_SHORT).show()
        }
    }

    // Delete the user data from Firestore
    private fun deleteData() {
        todoItemId?.let { _ ->
            AlertDialog.Builder(this)
                .setTitle("Delete Data")
                .setMessage("Are you sure you want to delete this Task?")
                .setPositiveButton("Yes"){_, _ ->
                    viewModel.selectedData.value?.let {
                        viewModel.deleteData(it)
                        Toast.makeText(this, "Task Deleted", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}
