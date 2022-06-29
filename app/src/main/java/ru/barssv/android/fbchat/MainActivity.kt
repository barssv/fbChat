package ru.barssv.android.fbchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import ru.barssv.android.fbchat.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var datebase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            val firstname = binding.name.text.toString()
            val lastname = binding.lastname.text.toString()
            val age = binding.age.text.toString()
            val login = binding.login.text.toString()

            datebase = FirebaseDatabase.getInstance().getReference("Users")
            val User = User(firstname, lastname, age, login)
            datebase.child(login).setValue(User).addOnSuccessListener {
                binding.name.text.clear()
                binding.lastname.text.clear()
                binding.age.text.clear()
                binding.login.text.clear()

                Toast.makeText(this,"Успешно сохранено", Toast.LENGTH_LONG).show()
            }.addOnFailureListener {
                    Toast.makeText(this,"Ошибка сохранения", Toast.LENGTH_LONG).show()
                }

        }

//        val database = Firebase.database
//        val myRef = database.getReference("message")
//        myRef.setValue("Hello, ")
    }
}