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

package org.alfresco.event.gateway;

import java.util.List;
import java.util.stream.Collectors;

import org.alfresco.event.gateway.config.FromRouteProperties;
import org.alfresco.event.gateway.config.ToRouteProperties;
import org.alfresco.event.gateway.config.ToRouteProperties.Branch;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.RouteDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Event gateway route builder.
 *
 * @author Jamal Kaabi-Mofrad
 */
@Component
public class GatewayRoute extends RouteBuilder
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayRoute.class);

    private static final String HEADER_TYPE_NAME = "typeClassName";

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
            LOGGER.debug("Setting camel Route - From: {}, To: {}, Branches: {}, defaultBranch: {}",
                        fromRouteProperties.getUri(), toRouteProperties.getUri(),
                        getBranchesUris(), toRouteProperties.getDefaultBranch().getUri());
        }

        RouteDefinition routeDefinition = from(fromRouteProperties.getUri());

        List<Branch> branches = toRouteProperties.getBranches();
        if (branches.isEmpty() && StringUtils.isEmpty(toRouteProperties.getUri()))
        {
            throw new RuntimeException("Target route is not specified.");
        }

        if (!branches.isEmpty())
        {
            ChoiceDefinition choice = routeDefinition.choice();
            for (Branch branch : branches)
            {
                Predicate isRequiredType = new EventTypePredicate(header(HEADER_TYPE_NAME).getExpression(), branch.getOnType());

                choice.when(isRequiredType)
                            .to(branch.getUri());
            }

            if (StringUtils.isEmpty(toRouteProperties.getDefaultBranch().getUri()))
            {
                choice.end();
            }
            else
            {
                choice.otherwise()
                            .to(toRouteProperties.getDefaultBranch().getUri())
                            .end();
            }
        }
        if (!StringUtils.isEmpty(toRouteProperties.getUri()))
        {
            routeDefinition.to(toRouteProperties.getUri());
        }
    }

    private String getBranchesUris()
    {
        return toRouteProperties.getBranches().stream().map(Branch::getUri).collect(Collectors.joining(", "));
    }
}
