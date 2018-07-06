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
package org.alfresco.event.gateway;

import org.alfresco.event.gateway.config.FromRouteProperties;
import org.alfresco.event.gateway.config.ToRouteProperties;
import org.alfresco.events.types.NodeEvent;
import org.alfresco.events.types.RepositoryEvent;
import org.alfresco.events.types.authority.AuthorityEvent;
import org.alfresco.events.types.permission.PermissionsEvent;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Event gateway route builder.
 *
 * @author Jamal Kaabi-Mofrad
 */
@Component
public class GatewayRoute extends RouteBuilder
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayRoute.class);

    private static final Predicate IS_PERMISSION_EVENT = new TypePredicate(PermissionsEvent.class);
    private static final Predicate IS_CONTENT_EVENT = new TypePredicate(NodeEvent.class);
    private static final Predicate IS_AUTHORITY_EVENT = new TypePredicate(AuthorityEvent.class);
    private static final Predicate IS_REPOSITORY_EVENT = new TypePredicate(RepositoryEvent.class);

    private final FromRouteProperties fromRouteProperties;
    private final ToRouteProperties toRouteProperties;

    @Autowired
    public GatewayRoute(FromRouteProperties fromRouteProperties, ToRouteProperties toRouteProperties)
    {
        this.fromRouteProperties = fromRouteProperties;
        this.toRouteProperties = toRouteProperties;
    }

    @Override
    public void configure()
    {
        if (LOGGER.isDebugEnabled())
        {
            LOGGER.debug("Setting camel Route - From: {}, To: {}",
                        fromRouteProperties.getUri(), toRouteProperties.getUri());
        }

        from(fromRouteProperties.getUri())
                    .unmarshal("rawDataFormat")
                    .choice()
                        .when(IS_PERMISSION_EVENT).bean("permissionEventEnricher")
                        .when(IS_CONTENT_EVENT).bean("nodeEventEnricher")
                        .when(IS_AUTHORITY_EVENT).bean("authorityEventEnricher")
                        .when(IS_REPOSITORY_EVENT).bean("repositoryEventEnricher")
                        .end()
                    .marshal("publicDataFormat")
                    .to(toRouteProperties.getUri());
    }
}
