/*
 * Copyright 2018 Alfresco Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 *    http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.alfresco.event.gateway.services.rest;

import org.alfresco.event.gateway.config.ExternalProperties;
import org.alfresco.event.gateway.config.amqp.AmqpToProperties;
import org.alfresco.event.gateway.services.EventTopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Event Gateway Event Topic Controller.
 *
 * @author Jared Ottley
 */
@RestController
@RequestMapping("/api/public/event-gateway/versions/1")
 public class EventTopicController
 {
    @Autowired
    AmqpToProperties amqpToProperties;

    @Autowired
    ExternalProperties externalProperties;

    @RequestMapping(value="/eventTopic", method=RequestMethod.OPTIONS)
    @ResponseBody
    public ResponseEntity<?> getEventGlobalTopic()
    {
        EventTopicEntity eventTopicEntity = new EventTopicEntity(amqpToProperties.getToRoute().getTopicName(), externalProperties.getExternalUrl());
        return new ResponseEntity<>(JsonBodyContentEntry.wrap(eventTopicEntity),HttpStatus.OK);
    }

 }