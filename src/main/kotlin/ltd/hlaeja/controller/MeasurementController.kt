package ltd.hlaeja.controller

import java.util.UUID
import ltd.hlaeja.service.DeviceDataService
import ltd.hlaeja.service.DeviceRegistryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
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
}
