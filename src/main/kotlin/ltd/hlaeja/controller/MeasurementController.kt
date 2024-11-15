package ltd.hlaeja.controller

import java.util.UUID
import ltd.hlaeja.library.deviceData.MeasurementData
import ltd.hlaeja.service.DeviceDataService
import ltd.hlaeja.service.DeviceRegistryService
import org.springframework.http.HttpStatus.CREATED
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/measurement")
class MeasurementController(
    private val deviceDataService: DeviceDataService,
    private val deviceRegistry: DeviceRegistryService,
) {

    @GetMapping("/unit-{unit}")
    suspend fun getUnitMeasurement(
        @PathVariable unit: UUID,
    ): Map<String, Number> = deviceRegistry.getClientDevice(unit)
        .let { (client, device) -> deviceDataService.getMeasurement(client, device).fields }

    @PostMapping("/unit-{unit}")
    @ResponseStatus(CREATED)
    suspend fun addUnitMeasurement(
        @PathVariable unit: UUID,
        @RequestBody measurement: Map<String, Number>,
    ) {
        deviceRegistry.getClientDevice(unit)
            .let { (client, device) ->
                deviceDataService.addMeasurement(
                    client,
                    MeasurementData.Request(
                        mutableMapOf(
                            "unit" to unit.toString(),
                            "device" to device.toString(),
                        ),
                        measurement,
                    ),
                )
            }
    }
}
