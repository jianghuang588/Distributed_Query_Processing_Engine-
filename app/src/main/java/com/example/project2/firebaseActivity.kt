package com.example.project2
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class firebaseActivity: AppCompatActivity() {
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var recyclerView: RecyclerView
    private  lateinit var recyclerViews0: RecyclerView
    private  lateinit var recyclerViews1: RecyclerView
    private  lateinit var recyclerViews2: RecyclerView
    private  lateinit var recyclerViews3: RecyclerView
    private  lateinit var recyclerViews4: RecyclerView
    private  lateinit var recyclerViews5: RecyclerView
    private lateinit var rate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)

        recyclerView = findViewById(R.id.recyclerView9)
        recyclerViews0 = findViewById(R.id.recyclerViews0)
        recyclerViews1 = findViewById(R.id.recyclerViews1)
        recyclerViews2 = findViewById(R.id.recyclerViews2)
        recyclerViews3 = findViewById(R.id.recyclerViews3)
        recyclerViews4 = findViewById(R.id.recyclerViews4)
        recyclerViews5 = findViewById(R.id.recyclerViews5)
        rate = findViewById(R.id.rateButton)
        firebaseDatabase = FirebaseDatabase.getInstance()
        val current: String = intent.getStringExtra("saveInput")!!
        getDataFromFirebase(current)

        rate.setOnClickListener {
            val intent: Intent = Intent(this, rateActivity::class.java)
            intent.putExtra("saveInput", current)
            startActivity(intent)
        }
    }
    fun getDataFromFirebase(current: String) {
        val currWord = current
        val wordManager = WordManager()
        val reference = firebaseDatabase.getReference("tweets/$currWord")
        val reference1 = firebaseDatabase.getReference("tweets1/$currWord")
        val reference2 = firebaseDatabase.getReference("tweets2/$currWord")
        val reference3 = firebaseDatabase.getReference("tweets3/$currWord")
        val reference4 = firebaseDatabase.getReference("tweets4/$currWord")
        val reference5 = firebaseDatabase.getReference("tweets5/$currWord")
        val reference6 = firebaseDatabase.getReference("tweets6/$currWord")
        val newReference = reference.push()
        val newReference1 = reference1.push()
        val newReference2 = reference2.push()
        val newReference3 = reference3.push()
        val newReference4 = reference4.push()
        val newReference5 = reference5.push()
        val newReference6 = reference6.push()
        val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
        val email: String = firebaseAuth.currentUser!!.email!!

        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
        // GlobalScope.launch(Dispatchers.IO) is reference from above website
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val word = wordManager.retrieveAntonyms(
                    apiKey = getString(R.string.api_key),
                    antonyms = current
                )

                val word1 = wordManager.retrieveSynonyms(
                    apiKey = getString(R.string.api_key),
                    synonyms = current
                )

                val word2 = wordManager.retrieveDefinition(
                    apiKey = getString(R.string.api_key),
                    definition = current
                )

                val word3 = wordManager.retrieveExampleSentence(
                    apiKey = getString(R.string.api_key),
                    word = current
                )

                val word4 = wordManager.retrieveRhyme(
                    apiKey = getString(R.string.api_key),
                    rhyme = current
                )

                val word5 = wordManager.retrieveWordSyllables(
                    apiKey = getString(R.string.api_key),
                    syllables = current
                )
                val word6 = wordManager.retrieveWordPronunciation(
                    apiKey = getString(R.string.api_key),
                    pronoun = current
                )
                    for (input in word) {
                        val task = input.task
                        val tweet = newWord(
                            task = email,
                            task1 = "antonyms:$task",
                            task2 = "",
                            task3 = ""
                        )
                        newReference.setValue(tweet)
                    }
                    for (input in word1) {
                        val task = input.source
                        val task1 = input.definition
                        val task2 = input.examples
                        val task3 = input.random
                        val task4 = input.syllable
                        val task5 = input.view5
                        val task6 = input.view6
                        val task7 = input.view7
                        val task8 = input.view8
                        val task9 = input.view9
                        val task10 = input.view10
                        val task11 = input.view11
                        val task12 = input.view12
                        val task13 = input.view13
                        val task14 = input.view14
                        val task15 = input.view15
                        val task16 = input.view16
                        val task17 = input.view17
                        val tweet = Words(
                            source = email,
                            definition = "synonyms:$task",
                            examples =  "synonyms:$task1",
                            random = "synonyms:$task2",
                            syllable = "synonyms:$task3",
                            view5 = "synonyms:$task4",
                            view6 = "synonyms:$task5",
                            view7 = "synonyms:$task6",
                            view8 = "synonyms:$task7",
                            view9 = "synonyms:$task8",
                            view10 = "synonyms:$task9",
                            view11 = "synonyms:$task10",
                            view12 = "synonyms:$task11",
                            view13 = "synonyms:$task12",
                            view14 = "synonyms:$task13",
                            view15 = "synonyms:$task14",
                            view16 = "synonyms:$task15",
                            view17 = "synonyms:$task16",
                            view18 = "synonyms:$task17",
                            view19 = "",
                            view20 = "",
                            view21 = "",
                            view22 = "",
                            view23 = ""
                        )
                        newReference1.setValue(tweet)
                    }
                    for (input in word2) {
                        val task = input.task
                        val task1 = input.task1
                        val tweet = newWord(
                            task = email,
                            task1 = "tone:$task",
                            task2 = "definition:$task1",
                            task3 = ""
                        )
                        newReference2.setValue(tweet)
                    }
                    for (input in word3) {
                        val task = input.source
                        val task1 = input.definition
                        val task2 = input.examples
                        val task3 = input.random
                        val task4 = input.syllable
                        val task5 = input.view5
                        val task6 = input.view6
                        val task7 = input.view7
                        val task8 = input.view8
                        val task9 = input.view9
                        val task10 = input.view10
                        val task11 = input.view11
                        val task12 = input.view12
                        val task13 = input.view13
                        val task14 = input.view14
                        val task15 = input.view15
                        val task16 = input.view16
                        val task17 = input.view17
                        val task18 = input.view18
                        val tweet = Words(
                            source = email,
                            definition = "example sentence of the current word",
                            examples =  task1,
                            random = task2,
                            syllable = task3,
                            view5 = task4,
                            view6 = task5,
                            view7 = task6,
                            view8 = task7,
                            view9 = task8,
                            view10 = task9,
                            view11 = task10,
                            view12 = task11,
                            view13 = task12,
                            view14 = task13,
                            view15 = task14,
                            view16 = task15,
                            view17 = task16,
                            view18 = task17,
                            view19 = task18,
                            view20 = task,
                            view21 = "",
                            view22 = "",
                            view23 = ""
                        )
                        newReference3.setValue(tweet)
                    }
                    for (input in word4) {
                        val task = input.source
                        val task1 = input.definition
                        val task2 = input.examples
                        val task3 = input.random
                        val task4 = input.syllable
                        val task5 = input.view5
                        val task6 = input.view6
                        val task7 = input.view7
                        val task8 = input.view8
                        val task9 = input.view9
                        val task10 = input.view10
                        val task11 = input.view11
                        val task12 = input.view12
                        val task13 = input.view13
                        val task14 = input.view14
                        val task15 = input.view15
                        val task16 = input.view16
                        val task17 = input.view17
                        val tweet = Words(
                            source = email,
                            definition = "rhyme:$task",
                            examples =  "rhyme:$task1",
                            random = "rhyme:$task2",
                            syllable = "rhyme:$task3",
                            view5 = "rhyme:$task4",
                            view6 = "rhyme:$task5",
                            view7 = "rhyme:$task6",
                            view8 = "rhyme:$task7",
                            view9 = "rhyme:$task8",
                            view10 = "rhyme:$task9",
                            view11 = "rhyme:$task10",
                            view12 = "rhyme:$task11",
                            view13 = "rhyme:$task12",
                            view14 = "rhyme:$task13",
                            view15 = "rhyme:$task14",
                            view16 = "rhyme:$task15",
                            view17 = "rhyme:$task16",
                            view18 = "rhyme:$task17",
                            view19 = "",
                            view20 = "",
                            view21 = "",
                            view22 = "",
                            view23 = ""
                        )
                        newReference4.setValue(tweet)
                    }
                    for (input in word5) {
                        val task = input.task
                        val task1 = input.task1
                        val task2 = input.task2

                        val tweet = newWord(
                            task = email,
                            task1 = task,
                            task2 = task1,
                            task3 = task2
                        )
                        newReference5.setValue(tweet)
                    }
                    for (input in word6) {
                        val task = input.task
                        val task1 = input.task1

                        val tweet = newWord(
                            task = email,
                            task1 = task,
                            task2 = task1,
                            task3 = ""
                        )
                        newReference6.setValue(tweet)
                }
            } catch (exception: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this@firebaseActivity,
                        "fail to retrieve word",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        reference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@firebaseActivity,
                    "Network error with database: ${databaseError.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tweets = mutableListOf<newWord>()
                dataSnapshot.children.forEach { data: DataSnapshot ->
                    val tweet: newWord? = data.getValue(newWord::class.java)
                    if (tweet != null) {
                        tweets.add(tweet)
                    }
                }

                recyclerView.layoutManager = LinearLayoutManager(this@firebaseActivity)
                val adapter = NewWordsAdapter(tweets)
                recyclerView.adapter = adapter
            }
        })
        reference1.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@firebaseActivity,
                    "Network error with database: ${databaseError.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tweets = mutableListOf<Words>()
                dataSnapshot.children.forEach { data: DataSnapshot->
                    val tweet: Words? = data.getValue(Words::class.java)
                    if (tweet != null) {
                        tweets.add(tweet)
                    }
                }
                recyclerViews0.layoutManager = LinearLayoutManager(this@firebaseActivity)
                val adapter0= WordsAdapter(tweets)
                recyclerViews0.adapter = adapter0

            }
        })
        reference2.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@firebaseActivity,
                    "Network error with database: ${databaseError.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tweets = mutableListOf<newWord>()
                dataSnapshot.children.forEach { data: DataSnapshot->
                    val tweet: newWord? = data.getValue(newWord::class.java)
                    if (tweet != null) {
                        tweets.add(tweet)
                    }
                }
                recyclerViews1.layoutManager = LinearLayoutManager(this@firebaseActivity)
                val adapter0= NewWordsAdapter(tweets)
                recyclerViews1.adapter = adapter0

            }
        })
        reference3.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@firebaseActivity,
                    "Network error with database: ${databaseError.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tweets = mutableListOf<Words>()
                dataSnapshot.children.forEach { data: DataSnapshot->
                    val tweet: Words? = data.getValue(Words::class.java)
                    if (tweet != null) {
                        tweets.add(tweet)
                    }
                }
                recyclerViews2.layoutManager = LinearLayoutManager(this@firebaseActivity)
                val adapter2= WordsAdapter(tweets)
                recyclerViews2.adapter = adapter2

            }
        })
        reference4.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@firebaseActivity,
                    "Network error with database: ${databaseError.message}",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tweets = mutableListOf<Words>()
                dataSnapshot.children.forEach { data: DataSnapshot->
                    val tweet: Words? = data.getValue(Words::class.java)
                    if (tweet != null) {
                        tweets.add(tweet)
                    }
                }
                recyclerViews3.layoutManager = LinearLayoutManager(this@firebaseActivity)
                val adapter3 = WordsAdapter(tweets)
                recyclerViews3.adapter = adapter3
            }
        })
        reference5.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@firebaseActivity,
                    "Network error with database: ${databaseError.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tweets = mutableListOf<newWord>()
                dataSnapshot.children.forEach { data: DataSnapshot->
                    val tweet: newWord? = data.getValue(newWord::class.java)
                    if (tweet != null) {
                        tweets.add(tweet)
                    }
                }
                recyclerViews4.layoutManager = LinearLayoutManager(this@firebaseActivity)
                val adapter4= NewWordsAdapter(tweets)
                recyclerViews4.adapter = adapter4
            }
        })
        reference6.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(
                    this@firebaseActivity,
                    "Network error with database: ${databaseError.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tweets = mutableListOf<newWord>()
                dataSnapshot.children.forEach { data: DataSnapshot->
                    val tweet: newWord? = data.getValue(newWord::class.java)
                    if (tweet != null) {
                        tweets.add(tweet)
                    }
                }
                recyclerViews5.layoutManager = LinearLayoutManager(this@firebaseActivity)
                val adapter5= NewWordsAdapter(tweets)
                recyclerViews5.adapter = adapter5
            }
        })
    }
}