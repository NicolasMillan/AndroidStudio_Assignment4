package ca.georgiancollege.assignmentthree

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ca.georgiancollege.assignmentthree.databinding.TodoListActivityBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: TodoListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Create a reference to the ActivityMainBinding class object
        binding = TodoListActivityBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        binding.todoListTitle.setOnClickListener {
//            val intent = Intent(this, TodoDetailsActivity::class.java)
//            startActivity(intent)
//        }
    }
}