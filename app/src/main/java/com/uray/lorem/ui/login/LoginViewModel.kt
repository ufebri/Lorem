package com.uray.lorem.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import febri.uray.bedboy.core.domain.usecase.AppUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: AppUseCase) : ViewModel() {

    fun doLogin(email: String, pwd: String) = useCase.getUser(email, pwd).asLiveData()
}