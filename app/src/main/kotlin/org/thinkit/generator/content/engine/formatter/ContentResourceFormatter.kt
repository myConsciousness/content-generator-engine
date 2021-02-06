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
import org.thinkit.generator.content.engine.catalog.ConditionKey
import org.thinkit.generator.content.engine.catalog.ConditionNodeKey
import org.thinkit.generator.content.engine.catalog.GroupKey
import org.thinkit.generator.content.engine.catalog.MetaKey
import org.thinkit.generator.content.engine.catalog.SelectionNodeKey
import org.thinkit.generator.content.engine.dto.ContentCondition
import org.thinkit.generator.content.engine.dto.ContentConditionNode
import org.thinkit.generator.content.engine.dto.ContentCreator
import org.thinkit.generator.content.engine.dto.ContentMatrix
import org.thinkit.generator.content.engine.dto.ContentMeta
import org.thinkit.generator.content.engine.dto.ContentResource
import org.thinkit.generator.content.engine.dto.ContentSelectionNode
import org.thinkit.generator.content.engine.factory.ContentItem
import org.thinkit.generator.content.engine.factory.ContentItemGroup
import org.thinkit.generator.content.engine.factory.ContentLeafVertex
import org.thinkit.generator.content.engine.factory.ContentNode
import org.thinkit.generator.content.engine.factory.ContentNodeGroup

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

        val contentMeta: ContentMeta = contentMatrix.contentMeta

        val leafVertex: ContentLeafVertex = ContentLeafVertex.newInstance()
        leafVertex.add(this.createMetaNodeGroup(contentMeta, contentMatrix.contentCreator))
        leafVertex.add(this.createSelectionNodeGroup(contentMatrix.contentSelectionNodes))

        if (!contentMatrix.contentConditionNodes.isEmpty()) {
            leafVertex.add(this.createConditionNodeGroup(contentMatrix.contentConditionNodes))
        }

        return when (contentMeta.formatResource) {
            true ->
                    ContentResource(
                            contentMeta.packageName,
                            contentMeta.contentName,
                            leafVertex.prettify().createResource())
            false ->
                    ContentResource(
                            contentMeta.packageName,
                            contentMeta.contentName,
                            leafVertex.createResource())
        }
    }

    /**
     * コンテンツのメタ部分のノードグループを生成し返却します。
     *
     * @param contentMeta コンテンツメタ定義
     * @param contentCreator コンテンツ作成者定義
     * @return メタ部分のノードグループ
     */
    private fun createMetaNodeGroup(
            contentMeta: ContentMeta,
            contentCreator: ContentCreator
    ): ContentNodeGroup {

        val itemGroup: ContentItemGroup = ContentItemGroup.newInstance()
        itemGroup.add(ContentItem.from(MetaKey.AUTHOR.getTag(), contentCreator.creator).withDoubleQuotes())
        itemGroup.add(ContentItem.from(MetaKey.ENCODING.getTag(), contentMeta.encoding).withDoubleQuotes())
        itemGroup.add(ContentItem.from(MetaKey.CONTENT_NAME.getTag(), contentMeta.contentName).withDoubleQuotes())
        itemGroup.add(ContentItem.from(MetaKey.DESCRIPTION.getTag(), contentMeta.description).withDoubleQuotes())

        return ContentNodeGroup.from(GroupKey.META.getTag()).add(ContentNode.from(itemGroup))
    }

    /**
     * コンテンツの選択ノード部分のノードグループを生成し返却します。
     *
     * @param selectionNodes コンテンツにおける選択ノードの定義集合
     * @return 選択ノード部分のノードグループ
     */
    private fun createSelectionNodeGroup(
            selectionNodes: List<ContentSelectionNode>
    ): ContentNodeGroup {

        val selectionNodeGroup: ContentNodeGroup =
                ContentNodeGroup.from(GroupKey.SELECTION_NODES.getTag()).toArray()

        selectionNodes.forEach {
            val itemGroup: ContentItemGroup = ContentItemGroup.newInstance()
            itemGroup.add(ContentItem.from(SelectionNodeKey.CONDITION_ID.getTag(), it.conditionId).withDoubleQuotes())

            it.contentSelections.forEach { itemGroup.add(ContentItem.from(it.key, it.value).withDoubleQuotes()) }

            selectionNodeGroup.add(
                    ContentNode.from(
                            contentNodeGroup =
                                    ContentNodeGroup.from(GroupKey.NODE.getTag())
                                            .add(ContentNode.from(itemGroup))))
        }

        return selectionNodeGroup
    }

    /**
     * コンテンツの条件ノード部分のノードグループを生成し返却します。
     *
     * @param conditionNodes コンテンツにおける条件ノードの定義集合
     * @return 条件ノード部分のノードグループ
     */
    private fun createConditionNodeGroup(
            conditionNodes: List<ContentConditionNode>
    ): ContentNodeGroup {

        val conditionNodeGroup: ContentNodeGroup =
                ContentNodeGroup.from(GroupKey.CONDITION_NODES.getTag()).toArray()

        conditionNodes.forEach {
            val itemGroup: ContentItemGroup = ContentItemGroup.newInstance()
            itemGroup.add(ContentItem.from(ConditionNodeKey.CONDITION_ID.getTag(), it.conditionId).withDoubleQuotes())
            itemGroup.add(ContentItem.from(ConditionNodeKey.EXCLUDE.getTag(), when (it.exclude) {
                    true -> "true";
                    false -> "false";
            }))

            val conditionGroup: ContentNodeGroup = this.createConditionGroup(it.contentConditions)

            conditionNodeGroup.add(
                    ContentNode.from(
                            contentNodeGroup =
                                    ContentNodeGroup.from(GroupKey.NODE.getTag())
                                            .add(ContentNode.from(itemGroup.add(conditionGroup)))))
        }

        return conditionNodeGroup
    }

    /**
     * コンテンツの条件部分のノードグループを生成し返却します。
     *
     * @param conditions コンテンツにおける条件の定義集合
     * @return 条件部分のノードグループ
     */
    private fun createConditionGroup(conditions: List<ContentCondition>): ContentNodeGroup {

        val conditionGroup: ContentNodeGroup =
                ContentNodeGroup.from(GroupKey.CONDITIONS.getTag()).toArray()

        conditions.forEach {
            val itemGroup: ContentItemGroup = ContentItemGroup.newInstance()
            itemGroup.add(ContentItem.from(ConditionKey.KEY.getTag(), it.key).withDoubleQuotes())
            itemGroup.add(ContentItem.from(ConditionKey.OPERATOR.getTag(), it.operator).withDoubleQuotes())
            itemGroup.add(ContentItem.from(ConditionKey.OPERAND.getTag(), it.operand).withDoubleQuotes())

            conditionGroup.add(ContentNode.from(itemGroup))
        }

        return conditionGroup
    }
}
