package br.com.alura.meetups.repositories

import br.com.alura.meetups.models.Device
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeviceRepository : CrudRepository<Device, String> {

    @Query("SELECT d.firebaseToken FROM Device d")
    fun findAllTokens(): List<String>

}