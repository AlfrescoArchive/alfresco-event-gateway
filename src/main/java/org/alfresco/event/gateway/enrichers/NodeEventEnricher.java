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

import java.util.List;

import org.alfresco.event.model.EventV1;
import org.alfresco.event.model.HierarchyEntry;
import org.alfresco.event.model.NodeResourceV1;
import org.alfresco.events.types.NodeEvent;
import org.springframework.stereotype.Component;

/**
 * @author Jamal Kaabi-Mofrad
 */
@Component
public class NodeEventEnricher implements EventEnricher<NodeEvent, NodeResourceV1>
{
    private static final String HIERARCHY_ENTRY_TYPE = "Node";

    @Override
    public EventV1<NodeResourceV1> enrich(NodeEvent event)
    {
        List<HierarchyEntry> hierarchyEntries = EnricherUtil.getHierarchyEntries(event.getParentNodeIds(),
                    parentId -> new HierarchyEntry(parentId, HIERARCHY_ENTRY_TYPE));
        NodeResourceV1 contentResource = new NodeResourceV1(event.getId(), hierarchyEntries, event.getNodeType());

        return new EventV1<>(event.getType(), generateStreamPosition(), event.getUsername(), contentResource);
    }
}
