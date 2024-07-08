package com.example.project2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SourceActivity : AppCompatActivity() {
    private  lateinit var recyclerView: RecyclerView
    private  lateinit var recyclerView0: RecyclerView
    private  lateinit var recyclerView1: RecyclerView
    private  lateinit var recyclerView3: RecyclerView
    private lateinit var firebaseDatabase: FirebaseDatabase


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sources)
        recyclerView = findViewById(R.id.recyclerView)
        firebaseDatabase = FirebaseDatabase.getInstance()
        val current: String = intent.getStringExtra("saveInput")!!
        val wordManager = WordManager()

        recyclerView.layoutManager = LinearLayoutManager(this)

            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
            // GlobalScope.launch(Dispatchers.IO) is reference from above website
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val word = wordManager.retrieveSynonyms(
                        apiKey = getString(R.string.api_key),
                        synonyms = current
                    )
                    val words = word.toList()
                    runOnUiThread{
                        val adapter = WordsAdapter(words)
                        recyclerView.adapter = adapter
                    }
                } catch (exception: Exception) {
                    runOnUiThread {
                        Toast.makeText(
                            this@SourceActivity,
                            "fail to retrieve synonyms source activity",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

        recyclerView0 = findViewById(R.id.recyclerView0)
        recyclerView0.layoutManager = LinearLayoutManager(this)

        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
        // GlobalScope.launch(Dispatchers.IO) is reference from above website
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val word = wordManager.retrieveAntonyms(
                    apiKey = getString(R.string.api_key),
                    antonyms = current
                )
                val words = word.toList()
                runOnUiThread{
                    val adapter = NewWordsAdapter(words)
                    recyclerView0.adapter = adapter
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@SourceActivity,
                        "fail to retrieve antonyms source activity",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        recyclerView1 = findViewById(R.id.newRecyclerView1)
        recyclerView1.layoutManager = LinearLayoutManager(this)

        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
        // GlobalScope.launch(Dispatchers.IO) is reference from above website
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val word = wordManager.retrieveDefinition(
                    apiKey = getString(R.string.api_key),
                    definition = current
                )
                val words = word.toList()
                runOnUiThread{
                    val adapter = NewWordsAdapter(words)
                    recyclerView1.adapter = adapter
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@SourceActivity,
                        "fail to retrieve definition source activity",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        recyclerView3 = findViewById(R.id.recyclerView3)
        recyclerView3.layoutManager = LinearLayoutManager(this)

        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
        // GlobalScope.launch(Dispatchers.IO) is reference from above website
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val word = wordManager.retrieveExampleSentence(
                    apiKey = getString(R.string.api_key),
                    word = current
                )
                val words = word.toList()
                runOnUiThread{
                    val adapter = WordsAdapter(words)
                    recyclerView3.adapter = adapter
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@SourceActivity,
                        "fail to retrieve example sentence on source activity",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}