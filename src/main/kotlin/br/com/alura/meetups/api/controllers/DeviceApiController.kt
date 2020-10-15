package br.com.alura.meetups.api.controllers

import br.com.alura.meetups.dto.DeviceRequest
import br.com.alura.meetups.dto.DeviceResponse
import br.com.alura.meetups.services.DeviceService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("api/devices")
class DeviceApiController(
        private val service: DeviceService
) {

    @PostMapping
    fun save(@RequestBody request: DeviceRequest): ResponseEntity<Any> {
        service.save(request.device)
        return ResponseEntity.ok().build()
    }

    @GetMapping
    fun findAll(): ResponseEntity<Iterable<DeviceResponse>> {
        val response = service.findAll()
                .map { DeviceResponse(it) }
        return ResponseEntity.ok(response)
    }

}