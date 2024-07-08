package com.example.project2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var  username: EditText

    private lateinit var  password: EditText

    private lateinit var  confirmPassword: EditText

    private lateinit var login: Button

    private lateinit var signUp: Button

    private lateinit var progressBar: ProgressBar

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()

        val preferences: SharedPreferences = getSharedPreferences(
            "android-user",
            Context.MODE_PRIVATE
        )
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        confirmPassword = findViewById(R.id.confirmPassword)
        login = findViewById(R.id.login)
        signUp = findViewById(R.id.signUp)
        progressBar = findViewById(R.id.progressBar)
        username.addTextChangedListener(textWatcher)
        password.addTextChangedListener(textWatcher)
        confirmPassword.addTextChangedListener(textWatcher)
        progressBar.visibility = View.GONE

        val saveUsername = preferences.getString("username", "")
        val savePassword = preferences.getString("password", "")
        val saveConfirmPassword = preferences.getString("confirmPassword", "")

        login.isEnabled = false

        username.setText(saveUsername)
        password.setText(savePassword)
        confirmPassword.setText(saveConfirmPassword)

        login.setOnClickListener {
            defusedButton()
            val username : String = username.text.toString()
            val password : String = password.text.toString()
            val confirmPassword: String = confirmPassword.text.toString()

            firebaseAuth
                .signInWithEmailAndPassword(username, password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    when {
                        task.isSuccessful -> {
                            val currentUser: FirebaseUser = firebaseAuth.currentUser!!
                            val email = currentUser.email

                            Toast.makeText(
                                this,
                                "Sign in as $email!",
                                Toast.LENGTH_SHORT
                            ).show()

                            preferences
                                .edit()
                                .putString("username", username)
                                .putString("password", password)
                                .putString("confirmPassword", confirmPassword)
                                .apply()

                            val intent: Intent = Intent(this, WordActivity::class.java)
                            startActivity(intent)
                        }
                        else -> {
                            progressDelaysLogin()
                            val exception = task.exception
                            Toast.makeText(
                                this,
                                "Fail to sign in $exception!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

        signUp.setOnClickListener {
            SignUpdelays()
            val username : String = username.text.toString()
            val password : String = password.text.toString()

            firebaseAuth
                .createUserWithEmailAndPassword(username,password)
                .addOnCompleteListener{ task: Task<AuthResult> ->
                    when {
                        task.isSuccessful -> {

                            val currentUser: FirebaseUser = firebaseAuth.currentUser!!
                            val email = currentUser.email

                            Toast.makeText(
                                this,
                                "Register successfully as $email!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else -> {
                            progressDelaysSynonyms()
                            when (val exception: Exception = task.exception!!) {
                                is FirebaseAuthInvalidCredentialsException -> {
                                    Toast.makeText(
                                        this,
                                        "Bad Credentials $exception!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                is FirebaseAuthUserCollisionException -> {
                                    Toast.makeText(
                                        this,
                                        "Already Register with this email!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> {
                                    progressDelaysSynonyms()
                                    Toast.makeText(
                                        this,
                                        "Register failed $exception!",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }
        }
        fun progressDelaysLogin() {
            progressBar.visibility = View.VISIBLE
            signUp.isEnabled = false
            login.isEnabled = false
            signUp.postDelayed({
                progressBar.visibility = View.GONE
                signUp.isEnabled = true
                login.isEnabled = true
                },3000)
        }
        fun progressDelaysSynonyms() {
            progressBar.visibility = View.VISIBLE
            signUp.isEnabled = false
                signUp.postDelayed({
                progressBar.visibility = View.GONE
                signUp.isEnabled = true
                },3000)
        }
        fun SignUpdelays() {
            progressBar.visibility = View.VISIBLE
            signUp.isEnabled = false
            login.isEnabled = false

            signUp.postDelayed({
                progressBar.visibility = View.GONE
                signUp.isEnabled = true
                login.isEnabled = true
            },3000)
        }
        fun authorizedButton() {
            signUp.isEnabled = true
            login.isEnabled = true
        }
        fun defusedButton() {
            progressBar.visibility = View.VISIBLE
            signUp.isEnabled = false
            login.isEnabled = false
        }
        override fun onBackPressed() {
            // https://stackoverflow.com/questions/42778381/changing-visibility-of-android-progressbar
            // I reference the below code from above website
            if (progressBar.visibility == View.VISIBLE) {
                progressBar.visibility = View.GONE
            } else {
                super.onBackPressed()
            }
            authorizedButton()
        }



    private val textWatcher = object  : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val username : String = username.text.toString()
            val password : String = password.text.toString()
            val confirmPassword: String = confirmPassword.text.toString()
            val enable: Boolean = username.trim().isNotEmpty() && password.trim().isNotEmpty() && confirmPassword.trim().isNotEmpty()
            login.isEnabled = enable

            if (password != confirmPassword ) {
                login.isEnabled = false
            }
        }
        override fun afterTextChanged(s: Editable?) {
        }
    }
}