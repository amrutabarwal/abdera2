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
package org.apache.abdera2.ext.sharing;

import java.util.List;

import org.apache.abdera2.common.Constants;
import org.apache.abdera2.common.anno.QName;
import org.apache.abdera2.factory.Factory;
import org.apache.abdera2.model.Element;
import org.apache.abdera2.model.Entry;
import org.apache.abdera2.model.ExtensibleElementWrapper;

@QName(value="conflicts",ns=SharingHelper.SSENS,pfx=SharingHelper.SSEPFX)
public class Conflicts extends ExtensibleElementWrapper {

    public Conflicts(Element internal) {
        super(internal);
    }

    public Conflicts(Factory factory, javax.xml.namespace.QName qname) {
        super(factory, qname);
    }

    public List<Entry> getEntries() {
        return getExtensions(Constants.ENTRY);
    }

    public void addEntry(Entry entry) {
        addExtension((Entry)entry.clone());
    }

}
