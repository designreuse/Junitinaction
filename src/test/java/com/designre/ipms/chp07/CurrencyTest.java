package com.designre.ipms.chp07;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.easymock.EasyMock.*;

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
public class CurrencyTest extends TestCase {

    ExchangeRate mockExchangeRate;

    @Before
    public void setUp() throws Exception {
    mockExchangeRate = createMock("exchangeRate", ExchangeRate.class);
    }


    @Test
    public void testToEuros() throws IOException {

        Currency testObject = new Currency(2.50, "USD");
        Currency expected = new Currency(3.75, "EUR");

        expect(mockExchangeRate.getRate("USD", "EUR")).andReturn(1.5);
        replay(mockExchangeRate);
        Currency actual = testObject.toEuros(mockExchangeRate);
        assertEquals(expected, actual);
    }

    @Test
    public void testToEurosCAD() throws IOException {

        Currency testObject = new Currency(2.50, "USD");
        Currency expected = new Currency(3.75, "EUR");

        expect(mockExchangeRate.getRate(matches("[A-Z][A-Z][A-Z]"), matches("[A-Z][A-Z][A-Z]"))).andReturn(1.5);
        replay(mockExchangeRate);
        Currency actual = testObject.toEurosCAD(mockExchangeRate);
        assertEquals(expected, actual);
    }

    @Test
    public void testExchangeRateServiceUnavailable() throws IOException {

        Currency testObject = new Currency(2.50, "USD");
        expect(mockExchangeRate.getRate("USD", "EUR")).andThrow(new IOException());
        replay(mockExchangeRate);
        Currency actual = testObject.toEuros(mockExchangeRate);
        assertNull(actual);



    }

    @After
    public void tearDown() throws Exception {

    }
}