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
