/**
 * Project Name : generator-commons<br>
 * File Name : DtoDefinitionGroup.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.vo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.thinkit.common.util.iterator.FluentIterator;
import org.thinkit.common.util.iterator.IterableNode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * DTO定義グループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * DtoDefinitionGroup dtoDefinitionGroup = DtoDefinitionGroup.of()
 *                                  .add(dtoDefinition1)
 *                                  .add(dtoDefinition2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DtoDefinitionGroup implements Iterable<DtoDefinition>, IterableNode<DtoDefinition>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -8768027467311196696L;

    /**
     * DTO定義グループ
     */
    @Getter
    private List<DtoDefinition> dtoDefinitionGroup;

    /**
     * DTO定義グループのサイズ
     */
    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private DtoDefinitionGroup() {
        this.dtoDefinitionGroup = new ArrayList<>(0);
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoDefinitionGroup DTO定義グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoDefinitionGroup(@NonNull DtoDefinitionGroup dtoDefinitionGroup) {
        this.dtoDefinitionGroup = new ArrayList<>(dtoDefinitionGroup.getDtoDefinitionGroup());
        this.size = dtoDefinitionGroup.size();
    }

    /**
     * {@link DtoDefinitionGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link DtoDefinitionGroup} クラスの新しいインスタンス
     */
    public static DtoDefinitionGroup of() {
        return new DtoDefinitionGroup();
    }

    /**
     * 引数として指定された {@code dtoDefinitionGroup} オブジェクトの情報をコピーした新しい
     * {@link DtoDefinitionGroup} クラスのインスタンスを生成し返却します。
     */
    public static DtoDefinitionGroup of(@NonNull DtoDefinitionGroup dtoDefinitionGroup) {
        return new DtoDefinitionGroup(dtoDefinitionGroup);
    }

    /**
     * 引数として渡された {@code dtoDefinition} を条件リストへ追加します。
     * <p>
     * この {@link DtoDefinitionGroup#add(DtoDefinition)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * DtoDefinitionGroup dtoDefinitionGroup = DtoDefinitionGroup.of()
     *                                  .add(dtoDefinition1)
     *                                  .add(dtoDefinition2);
     * </code>
     * </pre>
     *
     * @param dtoDefinition 条件オブジェクト
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoDefinitionGroup add(@NonNull DtoDefinition dtoDefinition) {
        this.dtoDefinitionGroup.add(dtoDefinition);
        size++;

        return this;
    }

    /**
     * {@link DtoDefinition} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link DtoDefinition} クラスを総称型として持つストリーム
     */
    public Stream<DtoDefinition> stream() {
        return this.dtoDefinitionGroup.stream();
    }

    @Override
    public List<DtoDefinition> nodes() {
        return this.dtoDefinitionGroup;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<DtoDefinition> iterator() {
        return FluentIterator.of(this);
    }
}