/**
 * Project Name : Generator<br>
 * File Name : DtoConstructorContext.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/08<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.factory.strategy.dtogenerator;

import java.util.List;

import org.thinkit.generator.rule.factory.resource.Parameter;
import org.thinkit.generator.rule.factory.resource.Process;
import org.thinkit.generator.rule.factory.strategy.resource.ConstructorContext;
import org.thinkit.generator.rule.factory.strategy.resource.ConstructorStrategy;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * DTOのコンストラクタ定義を生成する際のストラテジーを判断するコンテキストを実装した具象クラスです。<br>
 * {@link ConstructorContext}を実装し、{@link ConstructorStrategy}の処理を委譲しています。<br>
 * <br>
 * 以下の機能を提供しています。<br>
 * {@link #toParameter(List)}<br>
 * {@link #toProcess(List)}<br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #toParameter(List)
 * @see #toProcess(List)
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class DtoConstructorContext extends ConstructorContext {

    /**
     * コンストラクタ
     * 
     * @param constructorStrategy コンストラクタストラテジー
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DtoConstructorContext(@NonNull ConstructorStrategy constructorStrategy) {
        super(constructorStrategy);
    }

    @Override
    public String toParameter(@NonNull List<Parameter> parameters) {
        return super.getConstructorStrategy().toParameter(parameters);
    }

    @Override
    public String toProcess(@NonNull List<Process> processes) {
        return super.getConstructorStrategy().toProcess(processes);
    }
}