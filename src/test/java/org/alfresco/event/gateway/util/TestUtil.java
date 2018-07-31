/*
 * Copyright (C) 2005-2018 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */

package org.alfresco.event.gateway.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.springframework.util.FileCopyUtils;

/**
 * @author Jamal Kaabi-Mofrad
 */
public class TestUtil
{
    public static String getResourceFileAsString(String fileName) throws Exception
    {
        InputStream inputStream = TestUtil.class.getClassLoader()
                    .getResourceAsStream(fileName);
        StringWriter writer = new StringWriter();
        FileCopyUtils.copy(new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8.name())), writer);

        return writer.toString();
    }
}
