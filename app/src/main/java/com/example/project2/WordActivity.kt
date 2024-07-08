package com.example.project2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class WordActivity : AppCompatActivity() {
    private lateinit var loginForSource: Button
    private lateinit var inputs: EditText
    private lateinit var definitionLogin: Button
    private lateinit var definitionWordPageLogin: Button
    private lateinit var mapActivityButton: Button
    private lateinit var progressBar0: ProgressBar
    private lateinit var progressBar1: ProgressBar
    private lateinit var progressBar2: ProgressBar
    private lateinit var progressBar5: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharePreference = getSharedPreferences(
            "android-word",
            Context.MODE_PRIVATE
        )

        setContentView(R.layout.word_activity)
        loginForSource = findViewById(R.id.loginForSource)
        definitionLogin = findViewById(R.id.definitionLogin)
        inputs = findViewById(R.id.mainInput)
        definitionWordPageLogin = findViewById(R.id.definitionWordPageLogin)
        mapActivityButton = findViewById(R.id.mapActivityButton)
        progressBar0 = findViewById(R.id.progressBar0)
        progressBar0.visibility = View.GONE
        progressBar1 = findViewById(R.id.progressBar1)
        progressBar1.visibility = View.GONE
        progressBar2 = findViewById(R.id.progressBar2)
        progressBar2.visibility = View.GONE
        progressBar5 = findViewById(R.id.progressBar5)
        progressBar5.visibility = View.GONE
        val saveInputMain = sharePreference.getString("saveInput","")
        inputs.setText(saveInputMain)

        loginForSource.setOnClickListener {
            progressBar0.visibility = View.VISIBLE
            loginForSource.isEnabled = false
            val saveInput = inputs.text.toString()
            sharePreference
                .edit()
                .putString("saveInput",saveInput)
                .apply()
            val intent: Intent = Intent(this, SourceActivity::class.java)
            intent.putExtra("saveInput", saveInput)
            startActivity(intent)
        }

        definitionLogin.setOnClickListener {
            progressBar1.visibility = View.VISIBLE
            definitionLogin.isEnabled = false
            val saveInput = inputs.text.toString()
            sharePreference
                .edit()
                .putString("saveInput",saveInput)
                .apply()
            val intent: Intent = Intent(this, DefinitionActivity::class.java)
            intent.putExtra("saveInput", saveInput)
            startActivity(intent)
        }

        definitionWordPageLogin.setOnClickListener {
            progressBar2.visibility = View.VISIBLE
            definitionWordPageLogin.isEnabled = false
            val saveInput = inputs.text.toString()
            sharePreference
                .edit()
                .putString("saveInput",saveInput)
                .apply()
            val intent: Intent = Intent(this, ExampleActivity::class.java)
            intent.putExtra("saveInput", saveInput)
            startActivity(intent)
        }

        mapActivityButton.setOnClickListener {
            progressBar5.visibility = View.VISIBLE
            mapActivityButton.isEnabled = false
            val saveInput = inputs.text.toString()
            sharePreference
                .edit()
                .putString("saveInput",saveInput)
                .apply()
            val intent: Intent = Intent(this, firebaseActivity::class.java)
            intent.putExtra("saveInput", saveInput)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        // https://stackoverflow.com/questions/42778381/changing-visibility-of-android-progressbar
        // I reference the below code from above website
        if (progressBar0.visibility == View.VISIBLE) {
            progressBar0.visibility = View.GONE
            loginForSource.isEnabled = true
        } else if (progressBar1.visibility == View.VISIBLE){
            progressBar1.visibility = View.GONE
            definitionLogin.isEnabled = true
        } else if (progressBar2.visibility == View.VISIBLE){
            progressBar2.visibility = View.GONE
            definitionWordPageLogin.isEnabled = true
        } else if (progressBar5.visibility == View.VISIBLE) {
            progressBar5.visibility = View.GONE
            mapActivityButton.isEnabled = true
        } else {
            super.onBackPressed()
        }
    }
}

