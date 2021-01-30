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

/**
 * コンテンツの各コンポーネントを抽象化したインターフェースです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
internal interface ContentComponent {

    /**
     * リソースを生成し文字列表現として返却する処理を定義するメソッドです。
     *
     * このメソッドを定義している {@link Component} を継承した具象サブクラスは必ずこのメソッドを実装する必要があります。
     *
     * @return 各要素を管理する部品で生成されたJavaリソース
     */
    fun createResource(): String
}
