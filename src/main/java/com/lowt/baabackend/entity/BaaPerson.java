package com.lowt.baabackend.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author LOW_TASTE
 * @since 2022-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaaPerson implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 唯一id
     */
    @Excel(name = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @Excel(name = "姓名", width = 20, needMerge = true)
    private String name;

    /**
     * 性别（0:女,1:男）
     */
    @Excel(name = "性别", width = 10, replace = {"男_1", "女_0"})
    private Integer gender;

    /**
     * 用药时间
     */
    @Excel(name = "用药日期", width = 20, format = "yyyy-MM-dd")
    private LocalDateTime medicationTime;

    /**
     * 出生时间
     */
    @Excel(name = "出生日期", width = 20, format = "yyyy-MM-dd")
    private LocalDateTime birthTime;

    /**
     * 适应症
     */
    @Excel(name = "适应症", width = 10, needMerge = true)
    private String indications;

    /**
     * 父亲身高
     */
    @Excel(name = "父亲身高", width = 20, needMerge = true)
    private Double fatherHeight;

    /**
     * 母亲身高
     */
    @Excel(name = "母亲身高", width = 20, needMerge = true)
    private Double motherHeight;

    /**
     * 初次身高
     */
    @Excel(name = "初始身高", width = 20, needMerge = true)
    private Double initHeight;

    /**
     * 初次体重
     */
    @Excel(name = "初始体重", width = 20, needMerge = true)
    private Double initWeight;

    /**
     * 逻辑删除字段（1:未删除,0:删除）
     */
    @TableLogic(value = "1", delval = "0")
    @TableField(fill = FieldFill.INSERT)
    private Integer isShow;

    @Excel(name = "初次IGF1用量", width = 20, needMerge = true)
    @TableField("init_IGF1")
    private Double initIgf1;

    @Excel(name = "初次Igfbp3用量", width = 20, needMerge = true)
    @TableField("init_IGFBP3")
    private Double initIgfbp3;

    /**
     * 记录创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modificationTime;

}
