package br.com.alura.meetups.services

import br.com.alura.meetups.models.Device
import br.com.alura.meetups.repositories.DeviceRepository
import org.springframework.stereotype.Service

@Service
class DeviceService(
        private val repository: DeviceRepository
) {

    fun save(device: Device): Device =
            repository.save(device)

    fun findAll(): Iterable<Device> = repository.findAll()

}
