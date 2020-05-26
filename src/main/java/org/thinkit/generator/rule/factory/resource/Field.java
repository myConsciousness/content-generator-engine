/**
 * Project Name : Generator<br>
 * File Name : Field.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.Getter;

/**
 * プログラムリソースにおけるフィールドを抽象化した抽象クラスです<br>
 * この抽象クラスではフィールドを生成するために必要な情報を保持します。
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class Field extends Component {

    /**
     * フィールドの説明
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private List<Description> descriptions = new ArrayList<>(0);

    /**
     * フィールドの定義
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private List<FieldDefinition> fieldDefinitions = new ArrayList<>(0);

    /**
     * デフォルトコンストラクタ
     */
    public Field() {
    }

    /**
     * フィールドの説明を追加します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param description フィールドの説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public void addDescription(Description description) {
        Objects.requireNonNull(description);
        this.descriptions.add(description);
    }

    /**
     * フィールドの定義を追加します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     * 
     * @param fieldDefinition フィールドの定義
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public void addFieldDefinition(FieldDefinition fieldDefinition) {
        Objects.requireNonNull(fieldDefinition);
        this.fieldDefinitions.add(fieldDefinition);
    }
}