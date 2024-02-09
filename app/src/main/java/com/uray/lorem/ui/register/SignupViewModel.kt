package com.uray.lorem.ui.register

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import febri.uray.bedboy.core.domain.model.User
import febri.uray.bedboy.core.domain.usecase.AppUseCase
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val useCase: AppUseCase) : ViewModel() {

    fun doSignUp(newUser: User) = useCase.insertNewUser(newUser)
}