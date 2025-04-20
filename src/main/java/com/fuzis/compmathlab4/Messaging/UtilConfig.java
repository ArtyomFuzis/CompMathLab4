package com.fuzis.compmathlab4.Messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig
{
    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }
}