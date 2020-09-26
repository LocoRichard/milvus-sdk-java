/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.milvus.client;

import javax.annotation.Nonnull;
import org.json.JSONObject;

/** Contains Json Parameter Builder */
public class JsonBuilder {
  private String paramsInJson;

  public JsonBuilder() {
    this.paramsInJson = "{}";
  }

  /**
   * Add key-value pair to paramsInJson.
   *
   * @param key The param key
   * @param value The param value
   * @return <code>JsonBuilder</code>
   */
  public JsonBuilder param(String key, Object value) {
    JSONObject jsonObject = new JSONObject(this.paramsInJson);
    jsonObject.put(key, value);
    this.paramsInJson = jsonObject.toString();
    return this;
  }

  /**
   * Add key-value pair to "params" in paramsInJson. Used by index.
   *
   * @param key The param key
   * @param value The param value
   * @return <code>JsonBuilder</code>
   */
  public JsonBuilder indexParam(String key, Object value) {
    JSONObject jsonObject = new JSONObject(this.paramsInJson);
    if (!jsonObject.has("params")) {
      jsonObject.put("params", new JSONObject().put(key, value));
    } else {
      ((JSONObject) jsonObject.get("params")).put(key, value);
    }
    this.paramsInJson = jsonObject.toString();
    return this;
  }

  public String build() {
    return this.paramsInJson;
  }
}