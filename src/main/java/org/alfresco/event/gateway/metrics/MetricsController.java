/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.metrics;

import org.apache.camel.CamelContext;
import org.apache.camel.component.metrics.routepolicy.MetricsRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jamal Kaabi-Mofrad
 */
@RestController
@RequestMapping("/alfresco/api/messaging/versions/1")
public class MetricsController
{
    private final CamelContext camelContext;

    @Autowired
    public MetricsController(CamelContext camelContext)
    {
        this.camelContext = camelContext;
    }

    @RequestMapping(path = "/metrics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHelloText()
    {
        MetricsRegistryService registryService = camelContext.hasService(MetricsRegistryService.class);
        if (registryService != null)
        {
            registryService.setPrettyPrint(true);
            return new ResponseEntity<>(registryService.dumpStatisticsAsJson(), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
