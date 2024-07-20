package ca.georgiancollege.assignmentthree

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.assignmentthree.databinding.TodoDetailsActivityBinding

class DetailsActivity: AppCompatActivity()
{
    private lateinit var binding: TodoDetailsActivityBinding
//    private val viewModel: ListViewModel by viewModels()
//    private val dataManager = DataManager
//    private var listID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        listID = intent.getStringExtra("listID")

//        if (listID != null) {
//            viewModel.loadListById(listID!!)
//        } else{
//            binding.buttonDelete.visibility = View.GONE
//        }

//        // Observe the LiveData from the ViewModel to update the UI
//        viewModel.listData.observe(this) { tvShow ->
//            tvShow?.let {
//                binding.editTextName.setText(it.name)
//                binding.editTextNotes.setText(it.date)
//                binding.switchCompleted.setChecked(it.completed)
//            }
//        }
//
//        binding.buttonSave.setOnClickListener {
//            saveTVShow()
//        }
//
//        binding.buttonDelete.setOnClickListener {
//            deleteTVShow()
//        }

        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }

    private fun saveTVShow() {
        TODO("Not yet implemented")
    }

    private fun deleteTVShow() {
        TODO("Not yet implemented")
    }
}