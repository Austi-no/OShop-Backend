package com.austin.Oshop.config.swagger;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.enums.*;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.security.*;
import org.springframework.context.annotation.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 23/Sep/2022 - 4:29 PM
 * @project Oshop
 */
@OpenAPIDefinition(info = @Info(title = "O-Shop", version = "1.0", description = "O-Shop API"))
@SecurityScheme(name = "Authorization", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
@Configuration

public class SwaggerConfig {



}
