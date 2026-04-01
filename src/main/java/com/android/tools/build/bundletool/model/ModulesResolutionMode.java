/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.android.tools.build.bundletool.model;

/** Controls how the modules specified via {@code --modules} are resolved during size calculation. */
public enum ModulesResolutionMode {
  /**
   * Treats --modules as additional to the installation time modules. Install-time modules and
   * their dependencies are always included alongside any explicitly requested modules.
   */
  TOTAL,

  /**
   * Uses only the explicitly specified modules. Install-time modules are not automatically included
   * and dependency resolution is skipped — the caller controls the exact set of modules to measure.
   */
  EXACT
}
