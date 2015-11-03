package com.designre.ipms.chp07;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertNull;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/* ========================================================================
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

public class WebClientTestEasyMock  {

    private ConnectionFactory factory;
    private InputStream stream;

    @Before
    public void setUp() throws Exception {
        factory = createMock( "factory", ConnectionFactory.class );
        stream  = createMock( "stream", InputStream.class );
    }

    @After
    public void tearDown() throws Exception {
        verify( factory );
        verify( stream );
    }

    @Test
    public void testGetContent() throws Exception {

        expect( factory.getData() ).andReturn(stream);
        expect(stream.read()).andReturn( new Integer( (byte) 'I'));
        expect( stream.read()).andReturn( new Integer( (byte) 't'));

        expect( stream.read()).andReturn( new Integer( (byte) 'W'));
        expect( stream.read()).andReturn( new Integer( (byte) 'o'));
        expect( stream.read()).andReturn( new Integer( (byte) 'r'));
        expect( stream.read()).andReturn( new Integer( (byte) 'k'));
        expect( stream.read()).andReturn( new Integer( (byte) 's'));
        expect( stream.read() ).andReturn(-1);
        stream.close();

        replay( factory );
        replay( stream );

        WebClient client = new WebClient();
        String result = client.getContent( factory );
        assertEquals("ItWorks", result);
    }

    @Test
    public void testGetContentCannotCloseInputStream() throws Exception {
        expect( factory.getData() ).andReturn( stream );
        expect( stream.read() ).andReturn(-1);
        stream.close();
        expectLastCall().andThrow(new IOException("cannot close"));

        replay( factory );
        replay( stream );
        WebClient client = new WebClient();
        String result = client.getContent( factory );

        assertNull(result);
    }
}