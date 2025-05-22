



   package com.alsaleh.reddit.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

    @Configuration
    public class SwaggerConfiguration {

        @Bean
        public OpenAPI redditCloneOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Reddit Clone API")
                            .version("1.0")
                            .description("API for Reddit Clone Application")
                            .contact(new Contact()
                                    .name("Mohammad alsaleh")
                                    .url("https://github.com/malsaleh88"))
                    );
        }
    }
