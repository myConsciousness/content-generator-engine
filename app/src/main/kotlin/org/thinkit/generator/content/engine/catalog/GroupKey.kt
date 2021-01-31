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

package org.thinkit.generator.content.engine.catalog

import org.thinkit.api.catalog.BiCatalog

/**
 * コンテンツのグループキーを管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
internal enum class GroupKey(private val code: Int, private val tag: String) :
        BiCatalog<GroupKey, String> {

    /** メタ */
    META(0, "meta"),

    /** 選択ノード群 */
    SELECTION_NODES(1, "selection_nodes"),

    /** 条件ノード群 */
    CONDITION_NODES(2, "condition_nodes"),

    /** ノnodesード */
    NODE(3, "node"),

    /** 条件群 */
    CONDITIONS(4, "conditions");

    override fun getCode(): Int = this.code

    override fun getTag(): String = this.tag
}
