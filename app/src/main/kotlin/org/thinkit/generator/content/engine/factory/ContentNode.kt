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

import org.thinkit.generator.content.engine.catalog.Brace

/**
 * コンテンノードを生成するファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
internal class ContentNode(
        private val contentItemGroup: ContentItemGroup,
        private val contentNodeGroup: ContentNodeGroup
) : ContentComponent {

    companion object {

        /**
         * 引数として渡された情報を基に {@linl ContentNode} クラスの新しいインスタンスを生成し返却します。
         *
         * @param contentItemGroup コンテンツ項目集合
         * @param contentNodeGroup コンテンツノード集合
         * @return {@link ContentNode} クラスの新しいインスタンス
         */
        fun from(
                contentItemGroup: ContentItemGroup,
                contentNodeGroup: ContentNodeGroup
        ): ContentNode {
            return ContentNode(contentItemGroup, contentNodeGroup)
        }
    }

    override fun createResource(): String {

        val node: StringBuilder = StringBuilder(0)

        node.append(Brace.START.getTag())
        node.append(this.contentItemGroup.createResource())
        node.append(this.contentNodeGroup.createResource())
        node.append(Brace.END.getTag())

        return node.toString()
    }
}
}
