/*
 * Copyright 2005-2018 Alfresco Software, Ltd. All rights reserved.
 *
 * License rights for this program may be obtained from Alfresco Software, Ltd.
 * pursuant to a written agreement and any use of this program without such an
 * agreement is prohibited.
 */

package org.alfresco.event.gateway.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Camel To route properties.
 *
 * @author Jamal Kaabi-Mofrad
 */
public class ToRouteProperties extends RouteProperties
{
    private final List<Branch> branches = new ArrayList<>();
    private final DefaultBranch defaultBranch = new DefaultBranch();

    public List<Branch> getBranches()
    {
        return branches;
    }

    public DefaultBranch getDefaultBranch()
    {
        return defaultBranch;
    }

    public static class Branch
    {
        private Class<?> onType;
        private String topicName;
        private String uri;

        public Class<?> getOnType()
        {
            return onType;
        }

        public void setOnType(Class<?> onType)
        {
            this.onType = onType;
        }

        public String getTopicName()
        {
            return topicName;
        }

        public void setTopicName(String topicName)
        {
            this.topicName = topicName;
        }

        public String getUri()
        {
            return uri;
        }

        public void setUri(String uri)
        {
            this.uri = uri;
        }
    }

    public static class DefaultBranch extends RouteProperties
    {
    }
}
