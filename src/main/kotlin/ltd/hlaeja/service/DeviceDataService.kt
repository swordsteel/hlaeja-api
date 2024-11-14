package ltd.hlaeja.service

import java.util.UUID
import ltd.hlaeja.library.deviceData.MeasurementData
import ltd.hlaeja.property.DeviceDataProperty
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.HttpStatus.REQUEST_TIMEOUT
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBodyOrNull
import org.springframework.web.server.ResponseStatusException

@Service
class DeviceDataService(
    private val webClient: WebClient,
    private val deviceDataProperty: DeviceDataProperty,
) {

    suspend fun getMeasurement(
        client: UUID,
        device: UUID,
    ): MeasurementData.Response = webClient.get()
        .uri("${deviceDataProperty.url}/client-$client/device-$device")
        .retrieve()
        .onStatus(NOT_FOUND::equals) { throw ResponseStatusException(NO_CONTENT) }
        .awaitBodyOrNull<MeasurementData.Response>() ?: throw ResponseStatusException(REQUEST_TIMEOUT)
}
