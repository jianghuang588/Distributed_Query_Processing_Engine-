package com.example.project2
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DefinitionActivity : AppCompatActivity() {
    private lateinit var NewRecyclerView0: RecyclerView
    private lateinit var NewRecyclerView1: RecyclerView
    private lateinit var NewRecyclerView2: RecyclerView
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_definition)

        val current: String = intent.getStringExtra("saveInput")!!
        val wordManager = WordManager()

        firebaseDatabase = FirebaseDatabase.getInstance()
        NewRecyclerView0 = findViewById(R.id.NewRecyclerView0)
        NewRecyclerView0.layoutManager = LinearLayoutManager(this)

        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
        // GlobalScope.launch(Dispatchers.IO) is reference from above website
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val word = wordManager.retrieveRhyme(
                    apiKey = getString(R.string.api_key),
                    rhyme = current
                )
                val words = word.toList()
                runOnUiThread {
                    val adapter = WordsAdapter(words)
                    NewRecyclerView0.adapter = adapter
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@DefinitionActivity,
                        "fail to retrieve definition source activity",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        NewRecyclerView1 = findViewById(R.id.NewRecyclerView1)
        NewRecyclerView1.layoutManager = LinearLayoutManager(this)

        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
        // GlobalScope.launch(Dispatchers.IO) is reference from above website
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val word = wordManager.retrieveWordSyllables(
                    apiKey = getString(R.string.api_key),
                    syllables = current
                )
                val words = word.toList()
                runOnUiThread {
                    val adapter = NewWordsAdapter(words)
                    NewRecyclerView1.adapter = adapter
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@DefinitionActivity,
                        "fail to retrieve syllable on syllable activity",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        NewRecyclerView2 = findViewById(R.id.NewRecyclerView2)
        NewRecyclerView2.layoutManager = LinearLayoutManager(this)

        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
        // GlobalScope.launch(Dispatchers.IO) is reference from above website
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val word = wordManager.retrieveWordPronunciation(
                    apiKey = getString(R.string.api_key),
                    pronoun = current
                )
                val words = word.toList()
                runOnUiThread {
                    val adapter = NewWordsAdapter(words)
                    NewRecyclerView2.adapter = adapter
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@DefinitionActivity,
                        "fail to retrieve pronoun on syllable activity",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}





