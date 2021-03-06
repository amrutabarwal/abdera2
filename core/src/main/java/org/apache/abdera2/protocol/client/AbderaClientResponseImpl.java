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
package org.apache.abdera2.protocol.client;

import javax.activation.MimeType;

import org.apache.abdera2.common.http.EntityTag;
import org.apache.abdera2.common.iri.IRI;
import org.apache.abdera2.common.protocol.ClientResponse;
import org.apache.abdera2.common.protocol.ClientResponseWrapper;
import org.apache.abdera2.model.Document;
import org.apache.abdera2.model.Element;
import org.apache.abdera2.parser.ParseException;
import org.apache.abdera2.parser.Parser;
import org.apache.abdera2.parser.ParserOptions;
import org.joda.time.DateTime;

class AbderaClientResponseImpl 
  extends ClientResponseWrapper implements AbderaClientResponse {
  
  public AbderaClientResponseImpl(
    ClientResponse response) {
    super(response);
  }
  
  protected AbderaSession getAbderaSession() {
    return (AbderaSession) this.getSession();
  }
  
  public <T extends Element> Document<T> getDocument() throws ParseException {
    return getDocument(getAbderaSession().getAbdera().getParser());
  }

  public <T extends Element> Document<T> getDocument(ParserOptions options)
      throws ParseException {
    return getDocument(getAbderaSession().getAbdera().getParser(),options);
  }

  public <T extends Element> Document<T> getDocument(Parser parser)
      throws ParseException {
      return getDocument(parser,null);
  }

  public <T extends Element> Document<T> getDocument(
    Parser parser,
    ParserOptions options) throws ParseException {
    try {
      if (options == null) 
      options = parser.getDefaultParserOptions();
      
      String charset = getCharacterEncoding();
      if (charset != null)
        options = options.usingCharset(charset);
      IRI cl = getContentLocation();
      if (cl != null && !cl.isAbsolute()) {
          IRI r = new IRI(getUri());
          cl = r.resolve(cl);
      }
      String base = (cl != null) ? cl.toASCIIString() : getUri();
      
      Document<T> doc = parser.parse(getInputStream(),base,options);
      EntityTag etag = getEntityTag();
      if (etag != null)
          doc.setEntityTag(etag);
      DateTime lm = getLastModified();
      if (lm != null)
          doc.setLastModified(lm);
      MimeType mt = getContentType();
      if (mt != null)
          doc.setContentType(mt.toString());
      String language = getContentLanguage();
      if (language != null)
          doc.setLanguage(language);
      String slug = getSlug();
      if (slug != null)
          doc.setSlug(slug);
      return doc;
    } catch (Throwable t) {
      throw new ParseException(t);
    }
  }

}