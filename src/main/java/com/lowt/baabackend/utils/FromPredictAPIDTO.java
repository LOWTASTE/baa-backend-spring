package com.lowt.baabackend.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FromPredictAPIDTO implements Serializable {

    private Long predict_age;
}
