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

import org.thinkit.generator.content.engine.dto.ContentMatrix
import org.thinkit.generator.content.engine.dto.ContentResource

/**
 * コンテンツリソースの整形を抽象化したインターフェースです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
interface ResourceFormatter {

    /**
     * 引数として渡された {@code contentMatrix} に指定されたデータを基にコンテンツのリソースを生成し返却します。
     *
     * @param contentMatrix コンテンツが定義されたマトリクス
     * @return {@code contentMatrix} に指定されたデータを基に生成されたコンテンツリソース
     */
    fun format(contentMatrix: ContentMatrix): ContentResource
}
