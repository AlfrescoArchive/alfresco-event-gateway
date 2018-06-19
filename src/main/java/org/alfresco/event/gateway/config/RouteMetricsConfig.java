/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.config;

import org.apache.camel.CamelContext;
import org.apache.camel.component.metrics.routepolicy.MetricsRoutePolicyFactory;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final MetricRegistry metricRegistry;

    @Autowired
    public RouteMetricsConfig(MetricRegistry metricRegistry)
    {
        this.metricRegistry = metricRegistry;
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
                factory.setMetricsRegistry(metricRegistry);
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
