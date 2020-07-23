/**
 * Project Name : generator-commons<br>
 * File Name : FunctionProcess.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/09<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.factory.resource;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * プログラムリソースにおける機能の処理を抽象化した抽象クラスです。<br>
 * この抽象クラスでは機能の処理定義に必要な情報を保持します。<br>
 * この抽象クラスを継承する具象クラスは必ず {@link Component#createResource()} を実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see Component
 * @see Component#createResource()
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class FunctionProcess extends Process {

    /**
     * コンストラクタ
     *
     * @param variableName 変数名
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected FunctionProcess(String variableName) {
        super(variableName);
    }
}