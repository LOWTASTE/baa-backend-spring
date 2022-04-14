package com.lowt.baabackend.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.view.PoiBaseView;
import com.lowt.baabackend.entity.BaaPerson;
import com.lowt.baabackend.service.BaaPersonService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/easyPoi")
public class ExcelController {
    /**
     * EasyPoi导入导出测试Controller
     * Created by LowTaste on 2021/10/12.
     */

    @Autowired
    BaaPersonService baaPersonService;

    @ApiOperation(value = "导出Excel")
    @GetMapping(value = "/exportXlsx")
    public void exportMemberList(ModelMap map, HttpServletRequest request,
                                 HttpServletResponse response) {
        List<BaaPerson> baaPersonList = baaPersonService.list();

        ExportParams params = new ExportParams("病人信息", "病人信息", ExcelType.XSSF);
        map.put(NormalExcelConstants.DATA_LIST, baaPersonList);
        map.put(NormalExcelConstants.CLASS, BaaPerson.class);
        map.put(NormalExcelConstants.PARAMS, params);
        map.put(NormalExcelConstants.FILE_NAME, "病人信息");
        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);
    }
}
