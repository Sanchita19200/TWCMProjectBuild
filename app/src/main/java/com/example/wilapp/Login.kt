package com.example.wilapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var useremail: EditText
    private lateinit var userpass :EditText

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        useremail = findViewById(R.id.editTextTextEmailAddress4)
        userpass = findViewById(R.id.editTextTextPassword)

        val loggingIn = findViewById<Button>(R.id.button)
        loggingIn.setOnClickListener {
            Dashboard()
        }

        val register = findViewById<TextView>(R.id.textView4)
        register.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

    }
    fun Dashboard()
    {
        val email = useremail.text.toString()
        val password = userpass.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
        } else {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        Toast.makeText(this, "Login successful for ${user?.email}", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, Mainmenu::class.java)
                        intent.putExtra("key", user?.email)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Login failed. Incorrect email or password.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

}