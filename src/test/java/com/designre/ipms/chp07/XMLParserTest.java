package com.designre.ipms.chp07;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
public class XMLParserTest extends TestCase {

    private XMLReader parser;

    @Before
    public void setUp() throws Exception {
        parser = XMLReaderFactory.createXMLReader();
    }

    public void testSimpleDoc() throws IOException, SAXException {
        String doc = "<root>\n  Hello World!\n</root>";
        ContentHandler mock = createStrictMock(ContentHandler.class);

        mock.setDocumentLocator((Locator) anyObject());
        expectLastCall().times(0, 1);
        mock.startDocument();
        mock.startElement(eq(""), eq("root"), eq("root"), (Attributes) anyObject());
        mock.characters((char[]) anyObject(), anyInt(), anyInt());
        expectLastCall().atLeastOnce();
        mock.endElement(eq(""), eq("root"), eq("root"));
        mock.endDocument();
        replay(mock);

        parser.setContentHandler(mock);
        InputStream in = new ByteArrayInputStream(doc.getBytes("UTF-8"));
        parser.parse(new InputSource(in));

        verify(mock);
    }

    @After
    public void tearDown() throws Exception {

    }
}