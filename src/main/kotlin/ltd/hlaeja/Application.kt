package ltd.hlaeja

import ltd.hlaeja.property.DeviceDataProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(DeviceDataProperty::class)
@SpringBootApplication
class Application

fun main(vararg args: String) {
    runApplication<Application>(*args)
}
