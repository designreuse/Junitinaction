package com.designre.ipms.chp07;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Rule;
import org.junit.Test;

import java.io.InputStream;

import static org.junit.Assert.assertEquals;

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
public class TestWebClientJMock{
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery()
    {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };
    @Test
    public void testGetContentOk() throws Exception
    {
        final ConnectionFactory factory = context.mock( ConnectionFactory.class);
        final InputStream mockStream    = context.mock( InputStream.class);
        context.checking( new Expectations()
        {
            {

                oneOf( factory ).getData();
                will(returnValue(mockStream));
                atLeast( 1 ).of( mockStream ).read();
                will(onConsecutiveCalls(
                        returnValue(new Integer((byte) 'I')),
                        returnValue(new Integer((byte) 't')),
                        returnValue(new Integer((byte) ' ')),
                        returnValue(new Integer((byte) 'W')),
                        returnValue(new Integer((byte) 'o')),
                        returnValue(new Integer((byte) 'r')),
                        returnValue(new Integer((byte) 'k')),
                        returnValue(new Integer((byte) 's')),
                        returnValue(new Integer((byte) '!')),
                        returnValue(-1)));
                oneOf( mockStream ).close();

            }

        });

        WebClient client = new WebClient();
        String result = client.getContent(factory);
        assertEquals("It Works!", result);

    }


}