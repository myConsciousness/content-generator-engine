/*
 * Copyright 2021 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.generator.content.engine.dto

import kotlinx.serialization.Serializable
import org.thinkit.framework.envali.annotation.RequireNonBlank
import org.thinkit.framework.envali.entity.ValidatableEntity

/**
 * コンテンツのメタデータを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@Serializable
data class ContentMeta(
        @RequireNonBlank val packageName: String,
        @RequireNonBlank val contentName: String,
        @RequireNonBlank val description: String,
        @RequireNonBlank val version: String,
        @RequireNonBlank var encoding: String = "UTF-8"
) : ValidatableEntity
