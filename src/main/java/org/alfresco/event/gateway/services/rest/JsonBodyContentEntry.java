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

/**
 * Wrapper to conform to <a href="https://ts.alfresco.com/share/page/site/eng/documentlibrary#filter=path%7C%2FArchitecture%2FV1%20REST%20API%20guidelines">Alfresco Public REST API Guidelines</a>.
 * 
 * @author Ray Gauss II
 */
public class JsonBodyContentEntry<T>
{
    private T entry;

    /**
     * Wraps an {@link Object} into {@link JsonBodyContentEntry}
     *
     * @param object The object to be wrapped
     * @return Wrapped object.
     */
    public static <T> JsonBodyContentEntry<T> wrap(T object)
    {
        // TODO consider throwing UnsupportedOperationException for null objects
        JsonBodyContentEntry<T> jsonBodyContentEntry = new JsonBodyContentEntry<>();
        jsonBodyContentEntry.setEntry(object);
        return jsonBodyContentEntry;
    }

    public T getEntry()
    {
        return entry;
    }

    public void setEntry(T entry)
    {
        this.entry = entry;
    }
}