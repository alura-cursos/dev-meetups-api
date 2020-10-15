package br.com.alura.meetups.repositories

import br.com.alura.meetups.models.Event
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository : CrudRepository<Event, String> {

    fun findByUsersEmail(email: String): List<Event>

}
