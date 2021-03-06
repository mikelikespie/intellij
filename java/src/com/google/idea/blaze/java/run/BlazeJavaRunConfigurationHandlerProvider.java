/*
 * Copyright 2016 The Bazel Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.idea.blaze.java.run;

import com.google.common.collect.ImmutableSet;
import com.google.idea.blaze.base.model.primitives.Kind;
import com.google.idea.blaze.base.run.BlazeCommandRunConfiguration;
import com.google.idea.blaze.base.run.confighandler.BlazeCommandRunConfigurationHandler;
import com.google.idea.blaze.base.run.confighandler.BlazeCommandRunConfigurationHandlerProvider;

/** Java-specific handler provider for {@link BlazeCommandRunConfiguration}s. */
public class BlazeJavaRunConfigurationHandlerProvider
    implements BlazeCommandRunConfigurationHandlerProvider {

  private static final ImmutableSet<Kind> RELEVANT_RULE_KINDS =
      ImmutableSet.of(
          Kind.ANDROID_ROBOLECTRIC_TEST,
          Kind.ANDROID_LOCAL_TEST,
          Kind.JAVA_TEST,
          Kind.JAVA_BINARY,
          Kind.SCALA_BINARY,
          Kind.SCALA_TEST,
          Kind.SCALA_JUNIT_TEST);

  static boolean supportsKind(Kind kind) {
    return RELEVANT_RULE_KINDS.contains(kind);
  }

  @Override
  public boolean canHandleKind(Kind kind) {
    return supportsKind(kind);
  }

  @Override
  public BlazeCommandRunConfigurationHandler createHandler(BlazeCommandRunConfiguration config) {
    return new BlazeJavaRunConfigurationHandler(config);
  }

  @Override
  public String getId() {
    return "BlazeJavaRunConfigurationHandlerProvider";
  }
}
