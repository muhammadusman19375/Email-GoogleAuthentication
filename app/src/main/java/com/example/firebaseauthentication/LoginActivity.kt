package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private var email: EditText? = null
    private var password: EditText? = null
    private var login: Button? = null
    private var progressBar: ProgressBar? = null
    private var emailText: String? = null
    private var passwordText: String? = null
    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email_login)
        password = findViewById(R.id.password_login)
        login = findViewById(R.id.button_login)
        progressBar = findViewById(R.id.progressBar)

        login?.setOnClickListener(View.OnClickListener {

            progressBar!!.visibility = View.VISIBLE
            emailText = email!!.text.toString()
            passwordText = password!!.text.toString()

            mAuth.signInWithEmailAndPassword(emailText!!,passwordText!!)
                .addOnCompleteListener(OnCompleteListener { task->
                    if(task.isSuccessful){
                        progressBar!!.visibility = View.INVISIBLE

                        var intent = Intent(this@LoginActivity,DashboardActivity::class.java)
                        intent.putExtra("email",mAuth.currentUser!!.email)
                        intent.putExtra("uid",mAuth.currentUser!!.uid)
                        startActivity(intent)
                    }
                    else{
                        progressBar!!.visibility = View.INVISIBLE
                        email!!.setText("")
                        password!!.setText("")
                        Toast.makeText(this@LoginActivity,"Invalid email/password",Toast.LENGTH_SHORT).show()
                    }
                })

        })

    }
}