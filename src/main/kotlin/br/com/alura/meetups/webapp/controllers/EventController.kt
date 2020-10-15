package br.com.alura.meetups.webapp.controllers

import br.com.alura.meetups.models.Event
import br.com.alura.meetups.services.EventService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

private const val EVENT = "event"
private const val EVENTS = "events"

@Controller
@RequestMapping("events")
class EventController(private val service: EventService) {

    @GetMapping
    fun list() = ModelAndView("event/list")
            .apply {
                addObject(EVENTS, service.findAll())
            }

    @GetMapping("/form")
    fun form(event: Event) = ModelAndView("event/form")
            .apply {
                addObject(EVENTS, event)
            }

    @GetMapping("/form/{id}")
    fun formEdit(
            @PathVariable id: String,
            redirectAttributes: RedirectAttributes
    ) = RedirectView("/events/form")
            .apply {
                val event = service.findById(id)
                redirectAttributes.addFlashAttribute(EVENT, event)
            }

    @PostMapping("/form")
    fun save(event: Event): ModelAndView {
        service.save(event)
        return ModelAndView("redirect:/events")
                .apply {
                    addObject(EVENT, event)
                }
    }

    @GetMapping("{id}")
    fun details(@PathVariable id: String): ModelAndView = ModelAndView("event/details")
            .apply {
                val event: Event = service.findById(id) ?: Event()
                addObject(EVENT, event)
            }

}