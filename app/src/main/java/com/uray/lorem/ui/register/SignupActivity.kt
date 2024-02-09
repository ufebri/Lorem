package com.uray.lorem.ui.register

import android.os.Bundle
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.uray.lorem.R
import com.uray.lorem.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.core.domain.model.User

@AndroidEntryPoint
class SignupActivity : AppCompatActivity() {

    //init Binding
    private lateinit var binding: ActivitySignupBinding

    //init viewmodel
    private val viewModel: SignupViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            //set role
            etRole.setAdapter(
                ArrayAdapter(
                    this@SignupActivity,
                    android.R.layout.simple_list_item_1,
                    resources.getStringArray(
                        R.array.user_role
                    )
                )
            )


            etEmail.doAfterTextChanged { checkValidation() }
            etPwd.doAfterTextChanged { checkValidation() }
            etUsername.doAfterTextChanged { checkValidation() }
            etRole.doAfterTextChanged { checkValidation() }

            btnSignup.setOnClickListener {
                viewModel.doSignUp(
                    User(
                        userEmail = etEmail.text.toString(),
                        userPassword = etPwd.text.toString(),
                        userRole = etRole.text.toString(),
                        userUsername = etUsername.text.toString()
                    )
                )
                Toast.makeText(
                    this@SignupActivity,
                    getString(R.string.success_added_user), Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
    }

    private fun checkValidation() {
        binding.apply {
            btnSignup.isEnabled = Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString())
                .matches() && etPwd.text.toString().isNotEmpty() && etUsername.text.toString()
                .isNotEmpty() && etRole.text.toString().isNotEmpty()
        }
    }
}