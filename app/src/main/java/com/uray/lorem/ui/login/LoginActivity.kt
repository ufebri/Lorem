package com.uray.lorem.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.uray.lorem.R
import com.uray.lorem.databinding.ActivityLoginBinding
import com.uray.lorem.ui.home.HomeActivity
import com.uray.lorem.ui.register.SignupActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    //init Binding
    private lateinit var binding: ActivityLoginBinding

    //init viewmodel
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            //check validation
            etEmail.doAfterTextChanged { isValid() }
            etPwd.doAfterTextChanged { isValid() }


            //Do Login
            btnLogin.setOnClickListener {
                loginViewModel.doLogin(etEmail.text.toString(), etPwd.text.toString())
                    .observe(this@LoginActivity) { user ->
                        if (user.userEmail.isEmpty()) {
                            Toast.makeText(
                                this@LoginActivity,
                                getString(R.string.user_not_found),
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    HomeActivity::class.java
                                ).putExtra("account", user)
                            )
                        }
                    }
            }

            //Do Signup
            btnSignup.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            }
        }
    }

    private fun isValid() {
        binding.apply {
            btnLogin.isEnabled = Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString())
                .matches() && etPwd.text.toString().isNotEmpty()
        }
    }
}