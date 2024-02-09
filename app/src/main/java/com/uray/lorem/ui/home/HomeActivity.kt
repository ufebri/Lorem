package com.uray.lorem.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.uray.lorem.R
import com.uray.lorem.databinding.ActivityHomeNormalBinding
import com.uray.lorem.ui.edit.DetailRoleActivity
import dagger.hilt.android.AndroidEntryPoint
import febri.uray.bedboy.core.domain.model.User
import febri.uray.bedboy.core.util.ConstantConfig

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    //init Binding
    private lateinit var binding: ActivityHomeNormalBinding

    //init viewmodel
    private val viewModel: HomeViewModel by viewModels()

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeNormalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val logAccount = intent.getParcelableExtra<User>("account")
        val isAdminRole = logAccount?.userRole.equals(ConstantConfig.ADMIN_ROLE, true)

        binding.apply {
            rvData.apply {
                layoutManager = GridLayoutManager(context, 2)

                if (isAdminRole) {
                    val mAdapter = RoleAdapter(onClick = { mData ->
                        startActivity(
                            Intent(
                                this@HomeActivity,
                                DetailRoleActivity::class.java
                            ).putExtra("selectedUser", mData).putExtra("currentUser", logAccount)
                        )
                    })

                    adapter = mAdapter

                    viewModel.getAllUsers.observe(this@HomeActivity, Observer<List<User>> { mList ->
                        if (mList.isEmpty()) {
                            Toast.makeText(
                                this@HomeActivity,
                                getString(R.string.user_not_found),
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            mAdapter.submitList(mList)
                        }
                    })
                } else {
                    val mAdapter = ImageItemAdapter(onClick = { mData ->
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mData.url)))
                    })

                    adapter = mAdapter.withLoadStateFooter(footer = LoadingStateAdapter {
                        mAdapter.retry()
                    })

                    viewModel.getAlbums.observe(this@HomeActivity,
                        Observer { mData -> mAdapter.submitData(lifecycle, mData) })
                }
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (doubleBackToExitPressedOnce) {
                    finish()
                } else {
                    doubleBackToExitPressedOnce = true
                    Toast.makeText(this@HomeActivity, getString(R.string.logout), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }

        // Add the callback to the back button handler
        onBackPressedDispatcher.addCallback(this, callback)
    }
}