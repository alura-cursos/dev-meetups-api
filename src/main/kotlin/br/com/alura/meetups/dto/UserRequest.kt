package br.com.alura.meetups.dto

import br.com.alura.meetups.models.User

class UserRequest(
        val email: String,
        val user: User = User(email)
)