/**
 * Project Name :Generator<br>
 * File Name : ClassDefinitionMatrixReader.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/23<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.dtogenerator;

import com.google.common.flogger.FluentLogger;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.rule.AbstractRule;
import org.thinkit.common.util.workbook.FluentSheet;
import org.thinkit.common.util.workbook.FluentWorkbook;
import org.thinkit.generator.dtogenerator.ClassDefinitionMatrix;
import org.thinkit.generator.rule.Sheet;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Excelに記述された定義書シートからクラス定義マトリクス情報を抽出する処理を行うルールです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class ClassDefinitionMatrixReader extends AbstractRule {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * ファイルパス
     */
    @Getter(AccessLevel.PRIVATE)
    private String filePath = "";

    /**
     * クラス定義情報群
     */
    @Getter
    private ClassDefinitionMatrix classDefinitionMatrix = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ClassDefinitionMatrixReader() {
    }

    /**
     * コンストラクタ
     *
     * @param filePath DTO定義書のファイルパス
     * @exception IllegalArgumentException ファイルパスがnullまたは空文字列の場合
     */
    public ClassDefinitionMatrixReader(String filePath) {
        logger.atInfo().log("ファイルパス = (%s)", filePath);

        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("wrong parameter was given. File path is required.");
        }

        this.filePath = filePath;
    }

    /**
     * シート名定数
     */
    private enum SheetName implements Sheet {
        定義書;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Override
    public boolean execute() {
        logger.atInfo().log("START");

        final FluentWorkbook workbook = new FluentWorkbook.Builder().fromFile(this.getFilePath()).build();
        final FluentSheet sheet = workbook.sheet(SheetName.定義書.name());

        final ClassCreatorDefinitionReader classCreatorDefinitionReader = new ClassCreatorDefinitionReader(sheet);

        if (!classCreatorDefinitionReader.execute()) {
            logger.atSevere().log("クラス作成者情報の取得処理が異常終了しました。");
            return false;
        }

        final ClassNameDefinitionReader classNameDefinitionReader = new ClassNameDefinitionReader(sheet);

        if (!classNameDefinitionReader.execute()) {
            logger.atSevere().log("クラス名定義情報の取得処理が異常終了しました。");
            return false;
        }

        final ClassDefinitionReader classDefinitionReader = new ClassDefinitionReader(sheet);

        if (!classDefinitionReader.execute()) {
            logger.atSevere().log("クラス定義情報の取得処理が異常終了しました。");
            return false;
        }

        final ClassDefinitionMatrix classDefinitionMatrix = new ClassDefinitionMatrix(
                classNameDefinitionReader.getClassNameDefinition(),
                classCreatorDefinitionReader.getClassCreatorDefinition(),
                classDefinitionReader.getClassDefinitionList());

        this.classDefinitionMatrix = classDefinitionMatrix;

        logger.atInfo().log("クラス定義情報マトリクス = (%s)", classDefinitionMatrix);
        logger.atInfo().log("END");
        return true;
    }
}