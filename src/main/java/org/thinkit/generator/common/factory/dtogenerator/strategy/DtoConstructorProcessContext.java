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

package org.thinkit.generator.common.factory.dtogenerator.strategy;

import org.thinkit.generator.common.factory.resource.strategy.ConstructorProcessContext;
import org.thinkit.generator.common.factory.resource.strategy.ConstructorProcessStrategy;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * DTOのコンストラクタ処理定義を生成する際のストラテジーを判断するコンテキストを実装した具象クラスです。<br>
 * {@link ConstructorProcessContext} を実装し、{@link ConstructorProcessStrategy}
 * の処理を委譲しています。
 * <p>
 * 以下の機能を提供しています。<br>
 * {@link #toConstructorProcess(String, String)}<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #toConstructorProcess(String, String)
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class DtoConstructorProcessContext extends ConstructorProcessContext {

    /**
     * コンストラクタ
     *
     * @param constructorProcessStrategy コンストラクタ処理ストラテジー
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoConstructorProcessContext(@NonNull ConstructorProcessStrategy constructorProcessStrategy) {
        super(constructorProcessStrategy);
    }

    @Override
    public String toConstructorProcess(@NonNull String variableName, @NonNull String getterName) {
        return super.getConstructorProcessStrategy().toConstructorProcess(variableName, getterName);
    }
}