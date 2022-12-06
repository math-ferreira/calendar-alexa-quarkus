package com.calendar.automation.config

import io.quarkus.runtime.StartupEvent
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.flywaydb.core.Flyway
import javax.enterprise.context.ApplicationScoped
import javax.enterprise.event.Observes

@ApplicationScoped
class FlywayConfig {

    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
    var datasourceUrl: String? = null

    @ConfigProperty(name = "quarkus.datasource.username")
    var datasourceUsername: String? = null

    @ConfigProperty(name = "quarkus.datasource.password")
    var datasourcePassword: String? = null

    fun setupFlyway(@Observes evt: StartupEvent?) {

        val flyway =
            Flyway.configure()
                .baselineOnMigrate(true)
                .dataSource(
                    datasourceUrl,
                    datasourceUsername,
                    datasourcePassword
                ).load()
        flyway.migrate()

        println("Current version flyway: ${flyway.info().current().version}")
    }

}