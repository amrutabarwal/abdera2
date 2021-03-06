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
package org.apache.abdera2.ext.media;

import org.apache.abdera2.factory.Factory;
import org.apache.abdera2.model.Element;
import org.apache.abdera2.model.ElementWrapper;
import org.apache.abdera2.common.anno.QName;
import org.apache.abdera2.common.iri.IRI;
import static org.apache.abdera2.ext.media.MediaConstants.*;

@QName(value=LN_THUMBNAIL, 
    ns=MEDIA_NS,
    pfx=MEDIA_PREFIX)
public class MediaThumbnail extends ElementWrapper {

    public MediaThumbnail(Element internal) {
        super(internal);
    }

    public MediaThumbnail(Factory factory) {
        super(factory, MediaConstants.THUMBNAIL);
    }

    public IRI getUrl() {
        String url = getAttributeValue("url");
        return (url != null) ? new IRI(url) : null;
    }

    public void setUrl(String url) {
        if (url != null) {
            setAttributeValue("url", (new IRI(url)).toString());
        } else {
            removeAttribute("url");
        }
    }

    public int getWidth() {
        String width = getAttributeValue("width");
        return (width != null) ? Integer.parseInt(width) : -1;
    }

    public void setWidth(int width) {
        if (width > -1) {
            setAttributeValue("width", String.valueOf(width));
        } else {
            removeAttribute("width");
        }
    }

    public int getHeight() {
        String height = getAttributeValue("height");
        return (height != null) ? Integer.parseInt(height) : -1;
    }

    public void setHeight(int height) {
        if (height > -1) {
            setAttributeValue("height", String.valueOf(height));
        } else {
            removeAttribute("height");
        }
    }

    public String getTime() {
        return getAttributeValue("time");
    }

    public void setTime(String time) {
        if (time != null)
            setAttributeValue("time", time);
        else
            removeAttribute("time");
    }

}
