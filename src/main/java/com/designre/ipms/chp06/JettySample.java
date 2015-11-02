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
package com.designre.ipms.chp06;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ResourceHandler;

/**
 * A class that demonstrates how to create a sample Jetty embedded server.
 * 
 * @version $Id$
 */
public class JettySample
{
    public static void main( String[] args )
        throws Exception
    {
        Server server = new Server( 8080 );

        ContextHandler root = new ContextHandler( server, "/" );
        root.setResourceBase( "./pom.xml" );
        root.setHandler( new ResourceHandler() );

        server.setStopAtShutdown( true );
        server.start();
    }
}
