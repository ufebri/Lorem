package com.uray.lorem.ui.edit

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.uray.lorem.databinding.ActivityDetailRoleBinding
import com.uray.lorem.ui.component.GeneralAlertDialog
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.core.domain.model.User

@AndroidEntryPoint
class DetailRoleActivity : AppCompatActivity() {

    //init binding
    private lateinit var binding: ActivityDetailRoleBinding

    //init viewmodel
    private val viewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedUser = intent.getParcelableExtra<User>("selectedUser")
        val currentUser = intent.getParcelableExtra<User>("currentUser")


        binding.apply {
            //set role
            etRole.setAdapter(
                ArrayAdapter(
                    this@DetailRoleActivity, R.layout.simple_list_item_1, resources.getStringArray(
                        com.uray.lorem.R.array.user_role
                    )
                )
            )

            if (selectedUser != null) {
                etEmail.setText(selectedUser.userEmail)
                etUsername.setText(selectedUser.userUsername)
                etPwd.setText(selectedUser.userPassword)
                etRole.setText(selectedUser.userRole)
            }

            btnUpdate.setOnClickListener {
                viewModel.doUpdate(
                    User(
                        userID = selectedUser?.userID,
                        userEmail = etEmail.text.toString(),
                        userPassword = etPwd.text.toString(),
                        userRole = etRole.text.toString(),
                        userUsername = etUsername.text.toString()
                    )
                )
                Toast.makeText(
                    this@DetailRoleActivity,
                    getString(com.uray.lorem.R.string.update_user_success),
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }

            btnDelete.setOnClickListener {
                GeneralAlertDialog.showInputDialog(this@DetailRoleActivity,
                    getString(com.uray.lorem.R.string.alert_title_confirm_delete),
                    pwd = { pwd ->
                        if (currentUser?.userPassword.equals(pwd)) showConfirmDelete(selectedUser?.userID) else Toast.makeText(
                            this@DetailRoleActivity,
                            getString(com.uray.lorem.R.string.text_wrong),
                            Toast.LENGTH_LONG
                        ).show()
                    })
            }
        }
    }

    private fun showConfirmDelete(userID: Long?) {
        binding.apply {
            viewModel.doDelete(
                User(
                    userID = userID,
                    userEmail = etEmail.text.toString(),
                    userPassword = etPwd.text.toString(),
                    userRole = etRole.text.toString(),
                    userUsername = etUsername.text.toString()
                )
            )
            Toast.makeText(
                this@DetailRoleActivity,
                getString(com.uray.lorem.R.string.delete_user_success),
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }
}