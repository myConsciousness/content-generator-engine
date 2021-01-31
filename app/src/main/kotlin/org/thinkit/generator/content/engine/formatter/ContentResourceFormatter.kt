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

package org.thinkit.generator.content.engine.formatter

import org.thinkit.framework.envali.Envali
import org.thinkit.generator.content.engine.dto.ContentMatrix
import org.thinkit.generator.content.engine.dto.ContentMeta
import org.thinkit.generator.content.engine.dto.ContentResource
import org.thinkit.generator.content.engine.factory.ContentLeafVertex

/**
 * コンテンツ定義からコンテンツリソースへ整形する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
class ContentResourceFormatter : ResourceFormatter {

    companion object {

        /**
         * {@link ContentResourceFormatter} クラスの新しいインスタンスを生成し返却します。
         *
         * @return {@link ContentResourceFormatter} クラスの新しいインスタンス
         */
        fun newInstance(): ResourceFormatter {
            return ContentResourceFormatter()
        }
    }

    override fun format(contentMatrix: ContentMatrix): ContentResource {
        Envali.validate(contentMatrix)

        val leafVertex: ContentLeafVertex = ContentLeafVertex.newInstance()

        val contentMeta: ContentMeta = contentMatrix.contentMeta

        return ContentResource(
                contentMeta.packageName, contentMeta.contentName, leafVertex.createResource())
    }
}
