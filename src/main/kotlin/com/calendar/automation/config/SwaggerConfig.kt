package com.calendar.automation.config

import org.eclipse.microprofile.openapi.annotations.Components
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType
import org.eclipse.microprofile.openapi.annotations.info.Contact
import org.eclipse.microprofile.openapi.annotations.info.Info
import org.eclipse.microprofile.openapi.annotations.info.License
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.ws.rs.core.Application


@OpenAPIDefinition(
    info = Info(
        title = "Calendar Alexa Microservice",
        version = "1.0.0",
        contact = Contact(
            name = "Matheus da Silva Ferreira",
            url = "https://www.linkedin.com/in/math-ferreira/",
            email = "mat.s.ferreira@gmail.com"
        ),
        license = License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"
        )
    ),
    components = Components(
        securitySchemes = [
            SecurityScheme(
                securitySchemeName = "basic",
                type = SecuritySchemeType.HTTP,
                scheme = "basic"
            )
        ]
    ),
    security = [
        SecurityRequirement(name = "basic")
    ]
)
class SwaggerConfig : Application()