/**
 * Project Name : generator-commons<br>
 * File Name : DtoDefinition.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/05<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.common.vo.dto;

import java.util.ArrayList;
import java.util.List;

import org.thinkit.generator.common.catalog.dtogenerator.DtoItem;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO定義の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public final class DtoDefinition {

    /**
     * クラス名
     */
    private String className = "";

    /**
     * 説明
     */
    private String description = "";

    /**
     * クラス項目定義群
     */
    private List<DtoItem> dtoItemList = new ArrayList<>();

    /**
     * デフォルトコンストラクタ
     */
    public DtoDefinition() {
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoDefinition DTO定義情報
     */
    public DtoDefinition(@NonNull DtoDefinition dtoDefinition) {
        this.className = dtoDefinition.getClassName();
        this.description = dtoDefinition.getDescription();
        this.dtoItemList = new ArrayList<>(dtoDefinition.getDtoItemList());
    }
}
