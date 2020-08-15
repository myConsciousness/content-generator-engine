/*
 * Copyright 2020 Kato Shinya.
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

package org.thinkit.generator.common.factory.content;

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.json.ItemGroup;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * コンテンツの項目グループを生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class ContentItemGroup extends ItemGroup {

    @Override
    public String createResource() {

        final StringBuilder itemGroup = new StringBuilder();
        final String indent = Indentation.getIndentSpaces();
        final String returnCode = Indentation.returnCode();

        super.getItemGroup().forEach(item -> {
            itemGroup.append(indent).append(item.createResource()).append(returnCode);
        });

        itemGroup.setLength(itemGroup.length() - returnCode.length());

        return itemGroup.toString();
    }
}