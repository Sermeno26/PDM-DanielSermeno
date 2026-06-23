package com.example.labo6_pdm.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.labo6_pdm.data.model.Meal
import com.example.labo6_pdm.data.remote.RetrofitInstance
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    var users by mutableStateOf<List<Meal>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun loadUsers() {

        viewModelScope.launch {

            isLoading = true

            try {
                Log.d("Status","Vivo")

                users = RetrofitInstance
                    .api
                    .getUsers().meals

                Log.d("Status",users.toString())

            } catch (e: Exception) {

                e.printStackTrace()

            } finally {

                isLoading = false
            }
        }
    }
}