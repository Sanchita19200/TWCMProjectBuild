package com.example.wilapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class Pastdonations : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pastdonations)

        firestore = FirebaseFirestore.getInstance()

        val email = intent.getStringExtra("key")
        val message = "Past donations for :${email.toString()}"
        val message2 = findViewById<TextView>(R.id.messages)
        message2.text = message

        val listview  = findViewById<ListView>(R.id.pastlist)

        //making sure the user view only their past donations not others//
        // Query Firestore for past donations based on the email
        firestore.collection("donations")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                val pastDonations = mutableListOf<String>()

                for (document in documents) {
                    // Retrieve cardholderName and donationAmount from Firestore document
                    val cardholderName = document.getString("cardholderName")
                    val donationAmount = document.getDouble("donationAmount")

                    // Add the donation details to the list
                    if (cardholderName != null && donationAmount != null) {
                        pastDonations.add("$cardholderName : R$donationAmount")
                    }
                }

                // Display past donations in ListView
                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, pastDonations)
                listview.adapter = adapter
            }
            .addOnFailureListener { e ->
                // Handle errors
                message2.text = "Error retrieving past donations: ${e.message}"
            }
    }
}