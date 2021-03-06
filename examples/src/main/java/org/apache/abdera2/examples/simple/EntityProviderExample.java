/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  The ASF licenses this file to You
 * under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.  For additional information regarding
 * copyright in this work, please see the NOTICE file in the top level
 * directory of this distribution.
 */
package org.apache.abdera2.examples.simple;

import org.apache.abdera2.Abdera;
import org.apache.abdera2.protocol.EntityProvider;
import org.apache.abdera2.common.http.EntityTag;
import org.apache.abdera2.writer.StreamWriter;
import org.joda.time.DateTime;

/**
 * The EntityProvider interface allows developers to provide custom Object-to-Java serialization based on the
 * StreamWriter interface.
 */
public class EntityProviderExample {

    public static void main(String... args) throws Exception {
        Abdera abdera = Abdera.getInstance();
        StreamWriter sw = 
          abdera.create(StreamWriter.class)
                .setOutputStream(System.out)
                .setAutoIndent(true);
        Foo foo = new Foo();
        foo.writeTo(sw);
        sw.close();
    }

    public static class Foo implements EntityProvider {
        private static final String NS = "urn:foo";
        private String foo = "foo";
        private String bar = "bar";
        private String baz = "baz";

        public String getContentType() {
            return "application/foo+xml";
        }

        public EntityTag getEntityTag() {
            return EntityTag.generate(foo, bar, baz);
        }

        public DateTime getLastModified() {
            return null;
        }

        public boolean isRepeatable() {
            return true;
        }

        public void writeTo(StreamWriter sw) {
            sw.startDocument().startElement(foo, NS).startElement(bar, NS).startElement(baz, NS).endElement()
                .endElement().endElement().endDocument();
        }

    }

}
