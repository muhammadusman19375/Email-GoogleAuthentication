package com.example.firebaseauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {

    private var dummyEmail: TextView? = null
    private var dummyid: TextView? = null
    private var logoutBtn: Button? = null
    private var mAuth = FirebaseAuth.getInstance()
    lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        dummyid = findViewById(R.id.dummyId)
        dummyEmail = findViewById(R.id.dummyEmail)
        logoutBtn = findViewById(R.id.logoutBtn)

        dummyid!!.setText(intent.getStringExtra("uid"))
        dummyEmail!!.setText(intent.getStringExtra("email"))


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this@DashboardActivity, gso)



        logoutBtn!!.setOnClickListener(View.OnClickListener {

//..........................User for firebase email password authenticatoin.......................
//            mAuth.signOut()
//            startActivity(Intent(this@DashboardActivity,MainActivity::class.java))

            mGoogleSignInClient.signOut().addOnCompleteListener {
                val intent = Intent(this, googleSignActivity::class.java)
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                startActivity(intent)
                finish()
            }

        })

    }
}