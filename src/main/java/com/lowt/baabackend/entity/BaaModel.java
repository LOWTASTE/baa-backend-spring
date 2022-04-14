package com.lowt.baabackend.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class BaaModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private Double age;

    private Long modelId;

    private Long imgId;

    private LocalDateTime created;

    private Integer epoch;

    private String name;

    private Double accuracy_rate;

    private Integer algorithm;

    private String path;

    private String description;

}
