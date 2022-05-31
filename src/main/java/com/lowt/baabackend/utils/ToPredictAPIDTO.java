package com.lowt.baabackend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToPredictAPIDTO implements Serializable {

    private String pic_url;

    private Long model_id;
}
