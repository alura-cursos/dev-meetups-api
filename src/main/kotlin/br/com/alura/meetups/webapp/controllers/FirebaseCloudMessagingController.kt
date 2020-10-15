package br.com.alura.meetups.webapp.controllers


import br.com.alura.meetups.models.FirebaseData
import br.com.alura.meetups.services.FirebaseService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

private const val FIREBASE_MESSAGE = "firebaseMessage"
private const val FIREBASE_CONNECTION_STATE: String = "firebaseConnectionState"

@Controller
@RequestMapping("firebase/fcm")
class FirebaseCloudMessagingController(
        private val service: FirebaseService
) {

    @GetMapping("form")
    fun form() = ModelAndView("firebase/form").apply {
        addObject(FIREBASE_MESSAGE, FirebaseData())
        val firebaseConnectionState = service.connectionState
        addObject(FIREBASE_CONNECTION_STATE, firebaseConnectionState)
    }

    @PostMapping("send/data")
    fun sendData(
            message: FirebaseData,
            redirectAttributes: RedirectAttributes
    ) = RedirectView("/firebase/fcm/form")
            .also {
                service.sendData(message)
            }

    @PostMapping("send/notification")
    fun sendNotification(
            message: FirebaseData,
            redirectAttributes: RedirectAttributes
    ) = RedirectView("/firebase/fcm/form")
            .also {
                service.sendNotification(message)
            }

    @PostMapping("connect")
    fun connect() = RedirectView("/firebase/fcm/form")
            .also {
                service.setupFirebase()
            }

}