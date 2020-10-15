package br.com.alura.meetups.api.controllers

import br.com.alura.meetups.dto.EventResponse
import br.com.alura.meetups.dto.EventResponseWithSubscribers
import br.com.alura.meetups.dto.EventResponseWithSubscriptionStatus
import br.com.alura.meetups.dto.UserRequest
import br.com.alura.meetups.models.Event
import br.com.alura.meetups.models.User
import br.com.alura.meetups.services.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/events")
class EventApiController(private val service: EventService) {

    @GetMapping
    fun findAll(): ResponseEntity<Iterable<EventResponse>> =
            service.findAll()
                    .map { EventResponseWithSubscribers(it) }
                    .let { ResponseEntity.ok(it) }

    @GetMapping("{id}/subscribed")
    fun findEventSubscribedById(
            @PathVariable("id") id: String,
            @RequestParam("email", required = false) email: String?
    ): ResponseEntity<out EventResponse> =
            service.findById(id)?.let { event ->
                val response = email?.let { email ->
                    EventResponseWithSubscriptionStatus(event, email)
                } ?: EventResponseWithSubscribers(event)
                ResponseEntity.ok(response)
            } ?: ResponseEntity.notFound().build()

    @PostMapping
    fun save(@RequestBody event: Event): ResponseEntity<Event> =
            ResponseEntity.ok(service.save(event))

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") id: String): ResponseEntity<Any> {
        service.remove(id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("{id}/subscribe")
    fun subscribe(
            @PathVariable("id") id: String,
            @RequestBody request: UserRequest
    ): ResponseEntity<Any> {
        if (service.subscribe(id, request.user)) {
            return ResponseEntity.ok().build()
        }
        return ResponseEntity(HttpStatus.NOT_ACCEPTABLE)
    }

    @GetMapping("subscriptions")
    fun findAllSubscriptions(@RequestParam("email") email: String): ResponseEntity<List<EventResponseWithSubscriptionStatus>> {
        val subscriptions = service.findAllSubscriptions(email).map {
            EventResponseWithSubscriptionStatus(it, email)
        }
        return ResponseEntity.ok(subscriptions)
    }

    @PutMapping("{id}/unsubscribe")
    fun unsubscribe(
            @PathVariable id: String,
            @RequestBody user: User
    ): ResponseEntity<Any> {
        service.unsubscribe(id, user)
        return ResponseEntity.ok().build()
    }

}