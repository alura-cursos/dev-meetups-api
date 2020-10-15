package br.com.alura.meetups.services

import br.com.alura.meetups.models.Event
import br.com.alura.meetups.models.User
import br.com.alura.meetups.repositories.EventRepository
import org.springframework.stereotype.Service

@Service
class EventService(
        private val repository: EventRepository,
        private val firebaseService: FirebaseService
) {

    fun findAll(): Iterable<Event> =
            repository.findAll()

    fun save(event: Event): Event {
        findById(event.id)?.let { currentEvent ->
            return update(currentEvent, event)
        }
        return repository.save(event).apply {
            firebaseService.sendNewEvent(event)
        }
    }

    private fun update(
            currentEvent: Event,
            newEvent: Event
    ) = currentEvent.copy(
            title = newEvent.title,
            description = newEvent.description,
            image = newEvent.image
    ).let(repository::save)

    fun findById(id: String): Event? =
            repository.findById(id).orElse(null)

    fun remove(id: String) {
        findById(id)?.let {
            firebaseService.sendEventCancelation(it)
        }
        repository.deleteById(id)
    }

    fun subscribe(id: String, user: User): Boolean =
            findById(id)?.let { event ->
                val subscribed = event.subscribe(user)
                if (subscribed) {
                    repository.save(event)
                }
                subscribed
            } ?: false

    fun unsubscribe(id: String, user: User) {
        findById(id)?.let { event ->
            event.unsubscribe(user)
            save(event)
        }
    }

    fun findAllSubscriptions(email: String): List<Event> =
            repository.findByUsersEmail(email)
}
