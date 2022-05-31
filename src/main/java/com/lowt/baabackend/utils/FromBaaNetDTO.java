package com.lowt.baabackend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FromBaaNetDTO<E> implements Serializable {

    private Integer count;

    private String previous;

    private String next;

    private List<E> results;
}
