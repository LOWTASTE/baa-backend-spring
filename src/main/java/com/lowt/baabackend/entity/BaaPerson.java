package com.lowt.baabackend.entity;

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
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别（0:女,1:男）
     */
    private Integer gender;

    /**
     * 用药时间
     */
    private LocalDateTime medicationTime;

    /**
     * 出生时间
     */
    private LocalDateTime birthTime;

    /**
     * 适应症
     */
    private String indications;

    /**
     * 父亲身高
     */
    private Double fatherHeight;

    /**
     * 母亲身高
     */
    private Double motherHeight;

    /**
     * 初次身高
     */
    private Double initHeight;

    /**
     * 初次体重
     */
    private Double initWeight;

    /**
     * 逻辑删除字段（1:未删除,0:删除）
     */
    @TableLogic(value = "1", delval = "0")
    @TableField(fill = FieldFill.INSERT)
    private Integer isShow;

    @TableField("init_IGF1")
    private Double initIgf1;

    @TableField("init_IGFBP3")
    private Double initIgfbp3;

    /**
     * 记录创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modificationTime;

    private String address;

    private String telephoneNum;

    private String email;

    @TableField("IDcard")
    private String IDcard;
}
