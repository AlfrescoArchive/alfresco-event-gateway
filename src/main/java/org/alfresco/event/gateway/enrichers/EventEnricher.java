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
package org.alfresco.event.gateway.enrichers;

import org.alfresco.event.model.EventV1;
import org.alfresco.event.model.ResourceV1;
import org.alfresco.events.types.Event;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * Enriches an event.
 *
 * @author Jamal Kaabi-Mofrad
 */
public interface EventEnricher<T extends Event, R extends ResourceV1>
{

    EventV1<R> enrich(T event);

    default String generateStreamPosition()
    {
        String str = RandomStringUtils.randomAlphanumeric(6).toLowerCase();
        return System.currentTimeMillis() + "-" + str;
    }
}
