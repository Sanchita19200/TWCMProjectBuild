package com.example.wilapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class Donations : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donations)

        //preparing to upload to firebase//
        firestore = FirebaseFirestore.getInstance()

        //This is a message for the user//
        val email = intent.getStringExtra("key")
        val message = "Donate to the Cause : ${email.toString()}"
        val message2 = findViewById<TextView>(R.id.textView8)
        message2.text = message

        val cardholder = findViewById<EditText>(R.id.cardholder)
        val cardnumber = findViewById<EditText>(R.id.cardnumber)
        val cvv = findViewById<EditText>(R.id.cvv)
        val amount = findViewById<EditText>(R.id.amount)

        //this is for when the user is donating//
        button = findViewById(R.id.button3)
        button.setOnClickListener {

            // Retrieve user input from EditText fields
            val cardholderName = cardholder.text.toString()
            val cardNumber = cardnumber.text.toString()
            val cvvCode = cvv.text.toString()
            val donationAmount = amount.text.toString()

            // Validate input and exception handling//
            if (cardholderName.isNotEmpty() && cardNumber.isNotEmpty() && cvvCode.isNotEmpty() && donationAmount.isNotEmpty()) {
                // Additional validation
                if (cardNumber.length == 16 && cvvCode.length == 3) {
                    try {
                        val amountValue = donationAmount.toDouble()
                        if (amountValue > 0) {
                            // Processing the donation to Firebase Firestore

                            // Create a data object to be inserted into Firestore
                            val donationData = hashMapOf(
                                "email" to email,
                                "cardholderName" to cardholderName,
                                "donationAmount" to amountValue
                            )

                            // Add a document to the "donations" collection
                            firestore.collection("donations")
                                .add(donationData)
                                .addOnSuccessListener { documentReference ->
                                    Toast.makeText(
                                        this,
                                        "Donation successful! Document ID: ${documentReference.id}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(
                                        this,
                                        "Error adding donation: ${e.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                        } else {
                            // Display an error message for an invalid donation amount
                            Toast.makeText(
                                this,
                                "Please enter a valid donation amount",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: NumberFormatException) {
                        // Display an error message for an invalid donation amount format
                        Toast.makeText(
                            this,
                            "Please enter a valid donation amount",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    // Display an error message for an invalid card number or CVV
                    Toast.makeText(
                        this,
                        "Please enter a valid card number and CVV",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                // Display an error message if any of the fields are empty
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }
    }
}
}