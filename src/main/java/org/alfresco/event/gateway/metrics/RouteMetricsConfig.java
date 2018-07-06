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
package org.alfresco.event.gateway.metrics;

import org.apache.camel.CamelContext;
import org.apache.camel.component.metrics.MetricsComponent;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codahale.metrics.MetricRegistry;

/**
 * Auto-configuration of the Camel metrics component which allows collecting various metrics from Camel routes.
 *
 * @author Jamal Kaabi-Mofrad
 */
@Configuration
@ConditionalOnProperty(prefix = "messaging.metrics", name = "enabled", havingValue = "true")
public class RouteMetricsConfig
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteMetricsConfig.class);

    @Bean(name = MetricsComponent.METRIC_REGISTRY_NAME)
    public MetricRegistry metricRegistry()
    {
        return new MetricRegistry();
    }

    @Bean
    public CamelContextConfiguration contextConfiguration()
    {
        return new CamelContextConfiguration()
        {
            @Override
            public void beforeApplicationStart(CamelContext context)
            {
                LOGGER.info("Configuring camel metrics for all routes.");

                MetricsRoutePolicyFactory factory = new MetricsRoutePolicyFactory();
                factory.setMetricsRegistry(metricRegistry());
                context.addRoutePolicyFactory(factory);
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext)
            {
                //NOOP
            }
        };
    }
}
