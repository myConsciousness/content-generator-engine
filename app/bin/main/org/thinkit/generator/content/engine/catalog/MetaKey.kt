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

import org.thinkit.api.catalog.Catalog

/**
 * コンテンツのメタキーを管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
internal enum class MetaKey(private val code: Int) : Catalog<MetaKey> {

    /** 作成者 */
    AUTHOR(0),

    /** 作成日付 */
    CREATION_DATE(1),

    /** 更新日付 */
    UPDATE_DATE(2),

    /** パッケージ名 */
    PACKAGE_NAME(3),

    /** コンテンツ名 */
    CONTENT_NAME(4),

    /** エンコード方式 */
    ENCODING(5),

    /** 説明 */
    DESCRIPTION(6);

    override fun getCode(): Int = this.code
}
