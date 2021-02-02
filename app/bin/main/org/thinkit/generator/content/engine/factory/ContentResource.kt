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

import org.thinkit.formatter.JsonFormatter
import org.thinkit.generator.content.engine.catalog.Brace
import org.thinkit.generator.content.engine.catalog.FormatType

/**
 * コンテンツリソースを生成するファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
internal class ContentResource(private val leafVertex: ContentLeafVertex) : ContentComponent {

    /** 整形種別 */
    private var formatType: FormatType = FormatType.NONE

    companion object {

        /**
         * 引数として渡された情報を基に {@linl ContentResource} クラスの新しいインスタンスを生成し返却します。
         *
         * @param key キー名
         * @return {@link ContentResource} クラスの新しいインスタンス
         */
        fun from(leafVertex: ContentLeafVertex): ContentResource {
            return ContentResource(leafVertex)
        }
    }

    override fun createResource(): String {

        val resource: StringBuilder = StringBuilder(0)

        resource.append(Brace.START.getTag())
        resource.append(this.leafVertex.createResource())
        resource.append(Brace.END.getTag())

        return when (this.formatType) {
            FormatType.NONE -> resource.toString()
            FormatType.FORMAT -> JsonFormatter.of().format(resource.toString())
        }
    }

    /**
     * コンテンツの生成時に整形処理を行います。
     *
     * @return 自分自身のインスタンス
     */
    fun formatResource(): ContentResource {
        this.formatType = FormatType.FORMAT
        return this
    }
}
