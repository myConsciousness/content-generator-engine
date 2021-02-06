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

import org.thinkit.generator.content.engine.catalog.DataType

/**
 * コンテンツの項目を生成するファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
internal class ContentItem(private val key: String, private val value: String) : ContentComponent {

    /** データ型 */
    private var dataType: DataType = DataType.DEFAULT

    companion object {

        /**
         * 引数として渡されたデータを基に {@link ContentItem} クラスの新しいインスタンスを生成し返却します。
         *
         * @param key キー名
         * @param value 値
         * @return {@link ContentItem} クラスの新しいインスタンス
         */
        fun from(key: String, value: String): ContentItem {
            return ContentItem(key, value)
        }
    }

    override fun createResource(): String {
        return when (this.dataType) {
            DataType.DEFAULT -> "\"${key}\":${value}"
            DataType.STRING -> "\"${key}\":\"${value}\""
        }
    }

    /**
     * コンテンツ項目の値にダブルクオーテーションを付与します。
     *
     * @return 自分自身のインスタンス
     */
    fun withDoubleQuotes(): ContentItem {
        this.dataType = DataType.STRING
        return this
    }
}
