package br.com.alura.meetups.models

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
data class User(
        @Id
        val email: String,
        @ManyToMany
        val events: List<Event> = listOf()
)