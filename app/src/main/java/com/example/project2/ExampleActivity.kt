package com.example.project2

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ExampleActivity: AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var spinner1: Spinner
    private  lateinit var NewRecyclerView3: RecyclerView
    private lateinit var updateWord: EditText
    private lateinit var updateDictionaryWord : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)

        val current: String = intent.getStringExtra("saveInput")!!

        // https://developer.android.com/develop/ui/views/components/spinner
        // I reference the below code from above website
        spinner = findViewById(R.id.spinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinner.adapter = adapter
        }

        // https://developer.android.com/develop/ui/views/components/spinner
        // https://www.youtube.com/watch?v=D5l7MNlqA3M
        // I reference the below code from above website
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    1 -> {
                        spinner1 = findViewById(R.id.spinner1)
                        ArrayAdapter.createFromResource(
                            this@ExampleActivity,
                            R.array.spinner1,
                            android.R.layout.simple_spinner_item
                        ).also { adapter ->
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                            spinner1.adapter = adapter
                        }
                    }
                    2 -> {
                        ArrayAdapter.createFromResource(
                            this@ExampleActivity,
                            R.array.spinner2,
                            android.R.layout.simple_spinner_item
                        ).also { adapter ->
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                            spinner1.adapter = adapter
                        }
                    }
                    3 -> {
                        ArrayAdapter.createFromResource(
                            this@ExampleActivity,
                            R.array.spinner3,
                            android.R.layout.simple_spinner_item
                        ).also { adapter ->
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                            spinner1.adapter = adapter
                        }
                    }
                    4 -> {
                        ArrayAdapter.createFromResource(
                            this@ExampleActivity,
                            R.array.spinner4,
                            android.R.layout.simple_spinner_item
                        ).also { adapter ->
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                            spinner1.adapter = adapter
                        }
                    }
                    5 -> {
                        ArrayAdapter.createFromResource(
                            this@ExampleActivity,
                            R.array.spinner5,
                            android.R.layout.simple_spinner_item
                        ).also { adapter ->
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
                            spinner1.adapter = adapter
                        }
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        // https://developer.android.com/develop/ui/views/components/spinner
        // https://www.youtube.com/watch?v=D5l7MNlqA3M
        // I reference the below code from above website
        spinner = findViewById(R.id.spinner)
        spinner1 = findViewById(R.id.spinner1)
        NewRecyclerView3 = findViewById(R.id.NewRecyclerView3)
        updateWord = findViewById(R.id.updateWord)
        updateDictionaryWord = findViewById(R.id.updateDictionaryWord)

        NewRecyclerView3.layoutManager = LinearLayoutManager(this)
        val wordManager = WordManager()
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when {
                    spinner.selectedItemPosition == 1 && position == 1 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try{
                                val word = wordManager.retrieveRegionOf(
                                    apiKey = getString(R.string.api_key),
                                    RegionOf = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = WordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveRegionOf(
                                        apiKey = getString(R.string.api_key),
                                        RegionOf = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = WordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 1 && position == 2 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try{
                                val word = wordManager.retrieveInRegion(
                                    apiKey = getString(R.string.api_key),
                                    inRegion = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = NewWordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveInRegion(
                                        apiKey = getString(R.string.api_key),
                                        inRegion = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = NewWordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 1 && position == 3 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try{
                                val word = wordManager.retrieveHasUsages(
                                    apiKey = getString(R.string.api_key),
                                    hasUsages = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = WordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveHasUsages(
                                        apiKey = getString(R.string.api_key),
                                        hasUsages = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = WordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 1 && position == 4 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveUsageOf(
                                    apiKey = getString(R.string.api_key),
                                    UsageOf = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = NewWordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveUsageOf(
                                        apiKey = getString(R.string.api_key),
                                        UsageOf = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = NewWordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 2 && position == 1 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveHasMember(
                                    apiKey = getString(R.string.api_key),
                                    HasMember = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = NewWordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveHasMember(
                                        apiKey = getString(R.string.api_key),
                                        HasMember = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = NewWordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 2 && position == 2 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveMemberOf(
                                    apiKey = getString(R.string.api_key),
                                    MemberOf = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = NewWordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveMemberOf(
                                        apiKey = getString(R.string.api_key),
                                        MemberOf = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = NewWordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 2 && position == 3 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveHasCategories(
                                    apiKey = getString(R.string.api_key),
                                    HasCategories = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = WordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveHasCategories(
                                        apiKey = getString(R.string.api_key),
                                        HasCategories = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = WordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 2 && position == 4 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveInCategory(
                                    apiKey = getString(R.string.api_key),
                                    InCategory = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = NewWordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveInCategory(
                                        apiKey = getString(R.string.api_key),
                                        InCategory = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = NewWordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 3 && position == 1 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveIsATypeOf(
                                    apiKey = getString(R.string.api_key),
                                    word = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = WordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveIsATypeOf(
                                        apiKey = getString(R.string.api_key),
                                        word = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = WordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 3 && position == 2 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveWordType(
                                    apiKey = getString(R.string.api_key),
                                    wordType = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = WordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveWordType(
                                        apiKey = getString(R.string.api_key),
                                        wordType = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = WordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 3 && position == 3 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveHasSubstance(
                                    apiKey = getString(R.string.api_key),
                                    word = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = WordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveHasSubstance(
                                        apiKey = getString(R.string.api_key),
                                        word = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = WordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 3 && position == 4 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveSubstanceOf(
                                    apiKey = getString(R.string.api_key),
                                    SubstanceOf = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = NewWordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveSubstanceOf(
                                        apiKey = getString(R.string.api_key),
                                        SubstanceOf = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = NewWordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 4 && position == 1 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveAlso(
                                    apiKey = getString(R.string.api_key),
                                    also = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = NewWordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveAlso(
                                        apiKey = getString(R.string.api_key),
                                        also = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = NewWordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 4 && position == 2 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveSimilarTo(
                                    apiKey = getString(R.string.api_key),
                                    similarTo = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = WordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveSimilarTo(
                                        apiKey = getString(R.string.api_key),
                                        similarTo = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = WordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 4 && position == 3 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveEntail(
                                    apiKey = getString(R.string.api_key),
                                    entail = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = NewWordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveEntail(
                                        apiKey = getString(R.string.api_key),
                                        entail = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = NewWordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 5 && position == 1 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrievePartOfWord(
                                    apiKey = getString(R.string.api_key),
                                    word = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = WordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrievePartOfWord(
                                        apiKey = getString(R.string.api_key),
                                        word = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = WordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 5 && position == 2 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveHasPart(
                                    apiKey = getString(R.string.api_key),
                                    HasPart = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = WordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveHasPart(
                                        apiKey = getString(R.string.api_key),
                                        HasPart = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = WordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 5 && position == 3 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveIsAnInstance(
                                    apiKey = getString(R.string.api_key),
                                    IsAnInstance = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = NewWordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveIsAnInstance(
                                        apiKey = getString(R.string.api_key),
                                        IsAnInstance = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = NewWordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                    spinner.selectedItemPosition == 5 && position == 4 -> {
                        // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                        // GlobalScope.launch(Dispatchers.IO) is reference from above website
                        GlobalScope.launch(Dispatchers.IO) {
                            try {
                                val word = wordManager.retrieveHasInstances(
                                    apiKey = getString(R.string.api_key),
                                    HasInstances = current
                                )
                                val words = word.toList()
                                runOnUiThread {
                                    val adapter = WordsAdapter(words)
                                    NewRecyclerView3.adapter = adapter
                                }
                            } catch (exception: Exception) {
                                runOnUiThread {
                                    Toast.makeText(
                                        this@ExampleActivity,
                                        "fail to retrieve current word",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        updateDictionaryWord.setOnClickListener {
                            // https://stackoverflow.com/questions/65008486/globalscope-vs-coroutinescope-vs-lifecyclescope.
                            // GlobalScope.launch(Dispatchers.IO) is reference from above website
                            GlobalScope.launch(Dispatchers.IO) {
                                try {
                                    val word = wordManager.retrieveHasInstances(
                                        apiKey = getString(R.string.api_key),
                                        HasInstances = updateWord.text.toString()
                                    )
                                    val words = word.toList()
                                    runOnUiThread {
                                        val adapter = WordsAdapter(words)
                                        NewRecyclerView3.adapter = adapter
                                    }
                                } catch (exception: Exception) {
                                    runOnUiThread {
                                        Toast.makeText(
                                            this@ExampleActivity,
                                            "fail to retrieve input word",
                                            Toast.LENGTH_LONG
                                        ).show()
                                    }
                                }
                            }
                        }
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}
