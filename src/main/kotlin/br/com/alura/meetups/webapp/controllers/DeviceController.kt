package br.com.alura.meetups.webapp.controllers

import br.com.alura.meetups.services.DeviceService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("devices")
class DeviceController(
        private val service: DeviceService
) {

    @GetMapping
    fun findAll() = ModelAndView("device/list").apply {
        addObject("devices", service.findAll())
    }

}