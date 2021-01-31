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
 * コンテンツの項目集合を生成するファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
internal class ContentNodeGroup(private val key: String) : ContentComponent {

    /** コンテンツノードリスト */
    private val contentNodes: MutableList<ContentNode> = mutableListOf()

    override fun createResource(): String {

        val nodes: StringBuilder = StringBuilder(0)
        val comma: String = Delimiter.COMMA.getTag()

        this.contentNodes.forEach {
            nodes.append("\"{$key}\" : ")
            nodes.append(it.createResource()).append(comma)
        }

        nodes.setLength(nodes.length - comma.length)

        return nodes.toString()
    }

    /**
     * コンテンツノードを追加します。
     *
     * @param contentNode コンテンツノード
     * @return 自分自身のインスタンス
     */
    fun add(contentNode: ContentNode): ContentNodeGroup {
        this.contentNodes.add(contentNode)
        return this
    }
}
