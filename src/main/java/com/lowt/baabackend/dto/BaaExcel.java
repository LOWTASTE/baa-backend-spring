package com.lowt.baabackend.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.lowt.baabackend.entity.BaaPerson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaaExcel {
    @Excel(name = "病人信息")
    BaaPerson baaPerson;
}
