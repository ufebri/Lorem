package com.uray.lorem.ui.edit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import febri.uray.bedboy.core.domain.model.User
import febri.uray.bedboy.core.domain.usecase.AppUseCase
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(private val useCase: AppUseCase) : ViewModel() {

    fun doUpdate(user: User) = useCase.updateUser(user)

    fun doDelete(user: User) = useCase.deleteUser(user)
}