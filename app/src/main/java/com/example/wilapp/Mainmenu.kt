package com.example.wilapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class Mainmenu : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainmenu)

        // If the user has logged in, they are welcomed
        val email = intent.getStringExtra("key")
        val message = findViewById<TextView>(R.id.textView5)
        val welcome = "Thy Will Children's Mission \n Welcome $email"
        message.text = welcome

        //This is for the about us//
        val aboutus = findViewById<ImageView>(R.id.aboutus)
        aboutus.setOnClickListener {
            //passing email to be accessed in other classes//
            val intent = Intent(this, Aboutus::class.java)
            // Add the email as an extra to the new Intent
            intent.putExtra("key", email)
            startActivity(intent)
        }

        //These are for donating or viewing past donations//
        val donate = findViewById<Button>(R.id.button5)
        val viewpastdonation = findViewById<Button>(R.id.button6)

        donate.setOnClickListener {
            //passing email to be accessed in other classes//
            val intent = Intent(this, Donations::class.java)
            // Add the email as an extra to the new Intent
            intent.putExtra("key", email)
            startActivity(intent)
        }
        viewpastdonation.setOnClickListener {
            //passing email to be accessed in other classes//
            val intent = Intent(this, Pastdonations::class.java)
            // Add the email as an extra to the new Intent
            intent.putExtra("key", email)
            startActivity(intent)
        }
    }


    //this asks the user from if they logging out if back buttons presssed//
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout Confirmation")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
            startActivity(Intent(this, Login::class.java))
            finish() // Finishing the activity
        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
            // Dismiss the dialog and continue with the current activity
            dialogInterface.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

    fun Logout(view: View)
    {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout Confirmation")
        builder.setMessage("Are you sure you want to logout?")
        builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, _: Int ->
            startActivity(Intent(this, Login::class.java))
            finish() // Finishing the activity
        }
        builder.setNegativeButton("No") { dialogInterface: DialogInterface, _: Int ->
            // Dismiss the dialog and continue with the current activity
            dialogInterface.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}