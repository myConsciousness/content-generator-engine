/**
 * Project Name : generator-commons<br>
 * File Name : FunctionDescription.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/26<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラムリソースにおける関数の説明を抽象化した抽象クラスです。<br>
 * この抽象クラスでは関数の説明定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず{@link Component#createResource()}を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * @see Component
 * @see Description
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class FunctionDescription extends Description {

    /**
     * 引数の変数名
     */
    @NonNull
    @Getter(AccessLevel.PROTECTED)
    private List<FunctionParamAnnotation> functionParamAnnotations = new ArrayList<>(0);

    /**
     * コンストラクタ
     *
     * @param description 説明
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected FunctionDescription(String description) {
        super(description);
    }

    /**
     * 引数のアノテーション情報を追加します。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。
     *
     * @param functionParamAnnotation 引数のアノテーション情報
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public void add(FunctionParamAnnotation functionParamAnnotation) {
        Objects.requireNonNull(functionParamAnnotation);
        this.functionParamAnnotations.add(functionParamAnnotation);
    }

    /**
     * 関数の引数アノテーションが存在するか判定します。<br>
     * 関数の引数アノテーションが存在する場合は{@code true}、それ以外は{@false}を返却します。
     *
     * @return 関数の引数アノテーションが存在する場合は{@code true}、それ以外は{@false}
     */
    protected boolean hasParamAnnotation() {
        return !this.functionParamAnnotations.isEmpty();
    }
}