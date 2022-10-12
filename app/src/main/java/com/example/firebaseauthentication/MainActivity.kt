package com.example.firebaseauthentication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var email: EditText? = null
    private var password: EditText? = null
    private var submit: Button? = null
    private var alreadyRegistered: TextView? = null
    private var progressBar: ProgressBar? = null
    private var emailText: String? = null
    private var passwordText: String? = null
    private var mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)


        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        submit = findViewById(R.id.button)
        alreadyRegistered = findViewById(R.id.alreadyRegistered)
        progressBar = findViewById(R.id.progressBar)

        alreadyRegistered!!.setOnClickListener(View.OnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        })

        submit?.setOnClickListener(View.OnClickListener {

            progressBar!!.visibility = View.VISIBLE
            emailText = email!!.text.toString()
            passwordText = password!!.text.toString()

            mAuth.createUserWithEmailAndPassword(emailText!!, passwordText!!)
                .addOnCompleteListener(OnCompleteListener { task ->
                    if(task.isSuccessful){
                        progressBar!!.visibility = View.INVISIBLE
                        email!!.setText("")
                        password!!.setText("")
                        Toast.makeText(this@MainActivity,"Registered successfully",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        progressBar!!.visibility = View.INVISIBLE
                        email!!.setText("")
                        password!!.setText("")
                        Toast.makeText(this@MainActivity,"Problem occured",Toast.LENGTH_SHORT).show()
                    }
                })

        })

    }

}