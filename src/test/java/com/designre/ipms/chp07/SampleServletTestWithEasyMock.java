package com.designre.ipms.chp07;

import junit.framework.TestCase;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
@RunWith(EasyMockRunner.class)
public class SampleServletTestWithEasyMock extends EasyMockSupport {

    SampleServlet        sampleServlet;
    HttpServletRequest mockHttpServletRequest;
    HttpSession        mockHttpSession;

    @Before
    public void setUp(){

        sampleServlet          = new SampleServlet();
        mockHttpServletRequest = createStrictMock("mockHttpServletRequest", HttpServletRequest.class);
        mockHttpSession        = createStrictMock("mockHttpSession", HttpSession.class);
    }

    @Test
    public void testIsAuthenticated(){

        expect(mockHttpServletRequest.getSession(eq(false))).andReturn(mockHttpSession);
        expect(mockHttpSession.getAttribute(eq("authenticated"))).andReturn("true");

        replayAll();

        assertTrue(sampleServlet.isAuthenticated(mockHttpServletRequest));
    }
    @Test
    public void testIsAuthenticatedNotAuthenticated()
    {
        expect( mockHttpSession.getAttribute(eq("authenticated")) ).andReturn( "false" );
        expect( mockHttpServletRequest.getSession(eq(false)) ).andReturn(mockHttpSession);

        replayAll();

        assertFalse(sampleServlet.isAuthenticated(mockHttpServletRequest ) );
    }


    @After
    public void tearDown(){
        verifyAll();
    }

}