package com.example.project2

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class rateActivity : AppCompatActivity() {
    private lateinit var ratingBar : RatingBar
    private lateinit var image : ImageView
    private lateinit var summitButtonForRate : Button
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var newsRecyclerView30 : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate_card_view)
        ratingBar = findViewById(R.id.ratingBar)
        image = findViewById(R.id.image)
        summitButtonForRate = findViewById(R.id.summitButtonForRate)
        firebaseDatabase = FirebaseDatabase.getInstance()
        newsRecyclerView30 = findViewById(R.id.newsRecyclerView30)

        val current: String = intent.getStringExtra("saveInput")!!

        // https://www.youtube.com/watch?v=HdoJLQUj1ZY&t=696s
        // I reference the below code from above website
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            if (rating <= 1) {
                val imageOnDrawable = R.drawable.thinking_face_copy
                image.setImageResource(imageOnDrawable)
            } else if (rating <= 2) {
                val imageOnDrawable = R.drawable.thinking_face
                image.setImageResource(imageOnDrawable)
            } else if (rating <= 3) {
                val imageOnDrawable = R.drawable.slightly_smiling_face
                image.setImageResource(imageOnDrawable)
            } else if (rating <= 4) {
                val imageOnDrawable = R.drawable.smiling_face_with_heart_eyes
                image.setImageResource(imageOnDrawable)
            } else if (rating <= 5) {
                val imageOnDrawable = R.drawable.smiling_face_with_heart_shaped_eyes
                image.setImageResource(imageOnDrawable)
            }
            summitButtonForRate.setOnClickListener {
                getDataFromFirebase(current, rating.toString())
                if (rating <= 1) {
                    Toast.makeText(this, "Very sorry you had a bad experience. Your review is important to us.", Toast.LENGTH_SHORT).show()
                } else if (rating <= 2) {
                    Toast.makeText(this, "Sorry to hear that, we'll continue to improve.", Toast.LENGTH_SHORT).show()
                } else if (rating <= 3) {
                    Toast.makeText(this, "Thank you for your comments.", Toast.LENGTH_SHORT).show()
                } else if (rating <= 4) {
                    Toast.makeText(this, "That's wonderful.", Toast.LENGTH_SHORT).show()
                } else if (rating <= 5) {
                    Toast.makeText(this, "We're glad your needs are being met.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun getDataFromFirebase(current: String, rating: String) {
        val reference = firebaseDatabase.getReference("newTweet/$current")
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val id: String = firebaseAuth.currentUser!!.uid
        val newReference = reference.push()
        val email: String = firebaseAuth.currentUser!!.email!!
        val word = newWord(
            task = email,
            task1 = "ID:$id",
            task2 = "User Rating:$rating",
            task3 = ""
        )
        newReference.setValue(word)
        reference.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@rateActivity,
                    "Network error with database: ${databaseError.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tweets = mutableListOf<newWord>()
                dataSnapshot.children.forEach { data: DataSnapshot->
                    val tweet: newWord? = data.getValue(newWord::class.java)
                    if (tweet != null && data.exists()) {
                        tweets.add(tweet)
                    }
                }
                newsRecyclerView30.layoutManager = LinearLayoutManager(this@rateActivity)
                val adapter= NewWordsAdapter(tweets)
                newsRecyclerView30.adapter = adapter
            }
        })
    }
}