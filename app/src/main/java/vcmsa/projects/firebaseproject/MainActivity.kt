package vcmsa.projects.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var loginButton : Button

    private lateinit var registerTransitButton : Button
    private lateinit var passwordText : EditText
    private lateinit var emailText : EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        passwordText = findViewById(R.id.editTextPassword)
        emailText = findViewById(R.id.editTextEmailAddress)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailText.text.toString()
            val password = passwordText.text.toString()

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                        task -> if (task.isSuccessful) {
                    Toast.makeText(this, "Login Successful.", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, MainScreen::class.java)
                    startActivity(i)
                }
                else {
                    Toast.makeText(this, "Login Failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        registerTransitButton = findViewById(R.id.registerTransitButton)

        registerTransitButton.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
        }

    }
}