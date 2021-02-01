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
internal class ContentItemGroup : ContentComponent {

    /** コンテンツ項目リスト */
    private val contentItems: MutableList<ContentItem> = mutableListOf()

    companion object {

        /**
         * {@link ContentItemGroup} クラスの新しいインスタンスを生成し返却します。
         *
         * @return {@link ContentItemGroup} クラスの新しいインスタンス
         */
        fun newInstance(): ContentItemGroup {
            return ContentItemGroup()
        }
    }

    override fun createResource(): String {

        if (contentItems.isEmpty()) {
            return ""
        }

        val items: StringBuilder = StringBuilder(0)
        val comma: String = Delimiter.COMMA.getTag()

        contentItems.forEach {
            items.append(it.createResource())
            items.append(comma)
        }

        items.setLength(items.length - comma.length)

        return items.toString()
    }

    /**
     * コンテンツ項目を追加します。
     *
     * @param contentItem コンテンツ項目
     * @return 自分自身のインスタンス
     */
    fun add(contentItem: ContentItem): ContentItemGroup {
        contentItems.add(contentItem)
        return this
    }
}
