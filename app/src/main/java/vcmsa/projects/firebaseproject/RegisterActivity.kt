package vcmsa.projects.firebaseproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var loginTransitButton : Button
    private lateinit var registerButton : Button
    private lateinit var passwordText : EditText
    private lateinit var passwordConfirmText : EditText
    private lateinit var emailText : EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        passwordText = findViewById(R.id.editTextPassword)
        emailText = findViewById(R.id.editTextEmailAddress)
        passwordConfirmText = findViewById(R.id.editTextPasswordConfirm)
        registerButton = findViewById(R.id.registerButton)

        registerButton.setOnClickListener {
            val email = emailText.text.toString().trim()
            val password = passwordText.text.toString().trim()
            val confirmText = passwordConfirmText.text.toString().trim()

            print(email)
            print(password)
            print(confirmText)

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and Password must not be empty.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (confirmText != password) {
                Toast.makeText(this, "Password and Confirm Password must Match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.d("email", "$email")
            Log.d("password", "$password")

            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) {
                task -> if (task.isSuccessful) {
                    Toast.makeText(this, "Account Successfully Created.", Toast.LENGTH_SHORT).show()
                    finish()
                }
                else{
                Log.d("email", "$email")
                Log.d("password", "$password")
                    Toast.makeText(this, "Sorry, there was an error ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
        }




        loginTransitButton = findViewById(R.id.loginTransitButton)

        loginTransitButton.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }
}