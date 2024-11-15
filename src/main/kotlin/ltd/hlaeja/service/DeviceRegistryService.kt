package ltd.hlaeja.service

import java.util.UUID
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DeviceRegistryService {

    companion object {
        val temporaryClient: UUID = UUID.fromString("00000000-0000-0000-0000-000000000003")
        val temporaryDevice: UUID = UUID.fromString("00000000-0000-0000-0000-000000000002")
        val temporaryUnit: UUID = UUID.fromString("00000000-0000-0000-0000-000000000001")
    }

    suspend fun getClientDevice(unit: UUID): Pair<UUID, UUID> = if (unit == temporaryUnit) {
        temporaryClient to temporaryDevice
    } else {
        throw ResponseStatusException(NOT_FOUND)
    }
}
