// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package org.openqa.selenium.devtools.target.model;

import org.openqa.selenium.json.JsonInput;

import java.util.Objects;

public class AttachToTarget {

  private final SessionId sessionId;

  private final TargetId targetId;

  private final boolean waitForDebugger;

  public AttachToTarget(SessionId sessionId,
                        TargetId targetId, Boolean waitForDebugger) {
    this.sessionId = Objects.requireNonNull(sessionId, "sessionId is required");
    this.targetId = Objects.requireNonNull(targetId, "targetId is required");
    this.waitForDebugger = Objects.requireNonNull(waitForDebugger, "waitForDebugger is require");
  }

  private static AttachToTarget fromJson(JsonInput input) {
    SessionId sessionId = null;
    TargetId targetId = null;
    Boolean waitForDebugger = null;
    while (input.hasNext()) {
      switch (input.nextName()) {
        case "sessionId":
          sessionId = input.read(SessionId.class);
          break;
        case "targetId":
          targetId = input.read(TargetId.class);
          break;
        case "waitForDebugger":
          waitForDebugger = input.nextBoolean();
          break;
        default:
          input.skipValue();
          break;
      }
    }
    return new AttachToTarget(sessionId, targetId, waitForDebugger);
  }
}
