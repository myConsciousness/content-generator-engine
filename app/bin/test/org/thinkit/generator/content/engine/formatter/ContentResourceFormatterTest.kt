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

import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import org.junit.Test
import org.thinkit.generator.content.engine.dto.ContentCreator
import org.thinkit.generator.content.engine.dto.ContentMatrix
import org.thinkit.generator.content.engine.dto.ContentMeta
import org.thinkit.generator.content.engine.dto.ContentResource
import org.thinkit.generator.content.engine.dto.ContentSelection
import org.thinkit.generator.content.engine.dto.ContentSelectionNode

class ContentResourceFormatterTest {

    @Test
    fun testWhenSelectionNodeHasSingleItem() {

        val contentMeta: ContentMeta =
                ContentMeta(
                        packageName = "org.thinkit.content.generator.test",
                        contentName = "TestContent",
                        description = "Test description")

        val contentSelectionNode: ContentSelectionNode =
                ContentSelectionNode(
                        contentSelections = listOf(ContentSelection(key = "key", value = "value")))

        val contentMatrix: ContentMatrix =
                ContentMatrix(
                        contentMeta = contentMeta,
                        contentCreator = ContentCreator(creator = "Kato Shinya"),
                        contentSelectionNodes = listOf(contentSelectionNode))

        val contentResource: ContentResource =
                ContentResourceFormatter.newInstance().format(contentMatrix)

        assertNotNull(contentResource)
        assertEquals("TestContent", contentResource.contentName)
        assertEquals(EXPECTED_CONTENT_WITH_SINGLE_SELECTION_ITEM, contentResource.content)
        assertEquals("json", contentResource.extension)
    }

    /** 選択ノードが1つのみ項目を持っている場合の期待値 */
    val EXPECTED_CONTENT_WITH_SINGLE_SELECTION_ITEM: String =
            "{\"meta\":{\"author\":\"Kato Shinya\",\"encoding\":\"UTF-8\",\"content_name\":\"TestContent\",\"description\":\"Test description\"},\"selection_nodes\":{\"node\":{\"condition_id\":\"\",\"key\":\"value\"}}}"
}
