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
import org.thinkit.generator.content.engine.dto.ContentCondition
import org.thinkit.generator.content.engine.dto.ContentConditionNode
import org.thinkit.generator.content.engine.dto.ContentCreator
import org.thinkit.generator.content.engine.dto.ContentMatrix
import org.thinkit.generator.content.engine.dto.ContentMeta
import org.thinkit.generator.content.engine.dto.ContentResource
import org.thinkit.generator.content.engine.dto.ContentSelection
import org.thinkit.generator.content.engine.dto.ContentSelectionNode

/**
 * {@link ContentResourceFormatter} クラスのテストケースを管理するクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
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

    @Test
    fun testWhenSelectionNodeHasSingleItemWithCondition() {

        val contentMeta: ContentMeta =
                ContentMeta(
                        packageName = "org.thinkit.content.generator.test",
                        contentName = "TestContent",
                        description = "Test description")

        val contentSelectionNode: ContentSelectionNode =
                ContentSelectionNode(
                        conditionId = "0",
                        contentSelections = listOf(ContentSelection(key = "key", value = "value")))

        val contentConditionNode: ContentConditionNode =
                ContentConditionNode(
                        conditionId = "0",
                        contentConditions =
                                listOf(
                                        ContentCondition(
                                                key = "testKey", operator = "+", operand = "0")))

        val contentMatrix: ContentMatrix =
                ContentMatrix(
                        contentMeta = contentMeta,
                        contentCreator = ContentCreator(creator = "Kato Shinya"),
                        contentSelectionNodes = listOf(contentSelectionNode),
                        contentConditionNodes = listOf(contentConditionNode))

        val contentResource: ContentResource =
                ContentResourceFormatter.newInstance().format(contentMatrix)

        assertNotNull(contentResource)
        assertEquals("TestContent", contentResource.contentName)
        assertEquals(
                EXPECTED_CONTENT_WITH_SINGLE_SELECTION_ITEM_WITH_CONDITION, contentResource.content)
        assertEquals("json", contentResource.extension)
    }

    /** 選択ノードが1つのみ項目を持っている場合の期待値 */
    val EXPECTED_CONTENT_WITH_SINGLE_SELECTION_ITEM: String =
            "{\"meta\":{\"author\":\"Kato Shinya\",\"encoding\":\"UTF-8\",\"contentName\":\"TestContent\",\"description\":\"Test description\"},\"selectionNodes\":[{\"node\":{\"conditionId\":\"\",\"key\":\"value\"}}]}"

    /** 条件がある選択ノードが1つのみ項目を持っている場合の期待値 */
    val EXPECTED_CONTENT_WITH_SINGLE_SELECTION_ITEM_WITH_CONDITION: String =
            "{\"meta\":{\"author\":\"Kato Shinya\",\"encoding\":\"UTF-8\",\"contentName\":\"TestContent\",\"description\":\"Test description\"},\"selectionNodes\":[{\"node\":{\"conditionId\":\"0\",\"key\":\"value\"}}],\"conditionNodes\":[{\"node\":{\"conditionId\":\"0\",\"exclude\":\"false\",\"conditions\":[{\"key\":\"testKey\",\"operator\":\"+\",\"operand\":\"0\"}]}}]}"
}
