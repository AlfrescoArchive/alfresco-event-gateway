/*
 * Copyright 2018 Alfresco Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.alfresco.event.gateway.config;

import org.alfresco.event.databind.EventObjectMapperFactory;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Jamal Kaabi-Mofrad
 */
@Configuration
public class RouteConfig
{
    public static final ObjectMapper PUBLIC_OBJECT_MAPPER = EventObjectMapperFactory.createInstance();
    public static final ObjectMapper RAW_OBJECT_MAPPER = createRawEventObjectMapper();

    @Bean
    public DataFormat publicDataFormat()
    {
        return new JacksonDataFormat(PUBLIC_OBJECT_MAPPER, Object.class);
    }

    @Bean
    public DataFormat rawDataFormat()
    {
        return new JacksonDataFormat(RAW_OBJECT_MAPPER, Object.class);
    }

    private static ObjectMapper createRawEventObjectMapper()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.enableDefaultTyping(DefaultTyping.NON_FINAL, As.PROPERTY);
        return mapper;
    }
}
