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

package org.thinkit.generator.content.engine.factory

import org.thinkit.generator.content.engine.catalog.Delimiter

/**
 * コンテンツの葉頂点を生成するファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
internal class ContentLeadVertex : ContentComponent {

    /** コンテンツのノード全集合 */
    private val nodeGroups: MutableList<ContentNodeGroup> = mutableListOf()

    override fun createResource(): String {

        val leafVertex: StringBuilder = StringBuilder(0)
        val comma: String = Delimiter.COMMA.getTag()

        this.nodeGroups.forEach {
            leafVertex.append(it.createResource())
            leafVertex.append(comma)
        }

        leafVertex.setLength(leafVertex.length - comma.length)

        return leafVertex.toString()
    }

    /**
     * コンテンツノード集合を追加します。
     *
     * @param nodeGroup ノード集合
     * @return 自分自身のインスタンス
     */
    fun add(nodeGroup: ContentNodeGroup): ContentLeadVertex {
        this.nodeGroups.add(nodeGroup)
        return this
    }
}
