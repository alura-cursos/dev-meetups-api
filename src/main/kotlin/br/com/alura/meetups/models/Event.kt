package br.com.alura.meetups.models

import java.util.*
import javax.persistence.*

@Entity
data class Event(
        @Id
        val id: String = UUID.randomUUID().toString(),
        val title: String = "",
        @Column(columnDefinition = "text")
        val description: String = "",
        @Column(columnDefinition = "text")
        val image: String = "",
        @ManyToMany(cascade = [CascadeType.MERGE], fetch = FetchType.EAGER)
        private val users: MutableSet<User> = mutableSetOf(),
        val available: Boolean = true
) {

    val subscribers get() = users.toSet()

    fun subscribe(user: User): Boolean {
        if (users.contains(user)) {
            return false
        }
        users.add(user)
        return true
    }

    fun unsubscribe(user: User) {
        users.removeIf {
            it.email == user.email
        }
        println(users)
    }

    fun isSubscribed(email: String):
            Boolean = users.count { it.email == email } > 0

}