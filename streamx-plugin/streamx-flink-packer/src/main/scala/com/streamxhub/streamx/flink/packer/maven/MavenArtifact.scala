/*
 * Copyright (c) 2021 The StreamX Project
 * <p>
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.streamxhub.streamx.flink.packer.maven

import java.util.regex.Pattern

case class MavenArtifact(groupId: String, artifactId: String, version: String)

object MavenArtifact {
  private val p = Pattern.compile("([^: ]+):([^: ]+):([^: ]+)")

  /**
   * build from coords
   */
  def of(coords: String): MavenArtifact = {
    p.matcher(coords) match {
      case m if m.matches() =>
        val groupId = m.group(1)
        val artifactId = m.group(2)
        val version = m.group(3)
        MavenArtifact(groupId, artifactId, version)
      case _ =>
        throw new IllegalArgumentException(s"Bad artifact coordinates $coords, expected format is <groupId>:<artifactId>[:<extension>[:<classifier>]]:<version>")
    }
  }
}
