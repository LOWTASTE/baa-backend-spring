package com.lowt.baabackend.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BaaGh implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id 对应baa_person的id
     */
    @ApiModelProperty(name = "对应baa_person的id")
    private Long pId;


    /**
     * 唯一id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 记录创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 当月增长的高度
     */
    private Double gh;


}
