package br.com.alura.meetups.dto

import br.com.alura.meetups.models.Event

open class EventRequest(
        event: Event,
        val titulo: String = event.title,
        val descricao: String = event.description,
        val imagem: String? = event.image
)

open class EventResponse(
        event: Event,
        val id: String = event.id
) : EventRequest(
        event
)

open class EventResponseWithSubscribers(
        event: Event,
        id: String = event.id,
        val inscritos: Int = event.subscribers.size
) : EventResponse(
        event,
        id
)

class EventResponseWithSubscriptionStatus(
        event: Event,
        email: String,
        val estaInscrito: Boolean = event.isSubscribed(email)
) : EventResponseWithSubscribers(event)

