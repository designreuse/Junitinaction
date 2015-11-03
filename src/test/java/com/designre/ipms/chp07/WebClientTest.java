package com.designre.ipms.chp07;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;

/*
 * ========================================================================
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */

public class WebClientTest extends TestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();

    }

    @Test
    public void testGetContent() throws Exception {
        MockConnectionFactory mockConnectionFactory = new MockConnectionFactory();
        mockConnectionFactory.setData(new ByteArrayInputStream("It Works".getBytes()));

        WebClient client = new WebClient();
        String result = client.getContent(mockConnectionFactory);
        assertEquals("It Works", result);

    }

    @After
    public void tearDown() throws Exception {

    }


}