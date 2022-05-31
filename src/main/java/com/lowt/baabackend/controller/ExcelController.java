package com.lowt.baabackend.controller;

import com.lowt.baabackend.service.BaaPersonService;
import com.lowt.baabackend.utils.excel.ExcelBaaPersonInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/poi")
public class ExcelController {
    /**
     * EasyPoi导入导出测试Controller
     * Created by LowTaste on 2021/10/12.
     */

    @Autowired
    BaaPersonService baaPersonService;

    @Autowired
    ExcelBaaPersonInfo excelBaaPersonInfo;

    @ApiOperation(value = "导出Excel")
    @GetMapping(value = "/exportXlsx")
    public String exportMemberList(HttpServletRequest request,
                                   HttpServletResponse response) {
//        List<BaaPerson> baaPersonList = baaPersonService.list();
//
//        ExportParams params = new ExportParams("病人信息", "病人信息", ExcelType.XSSF);
//        map.put(NormalExcelConstants.DATA_LIST, baaPersonList);
//        map.put(NormalExcelConstants.CLASS, BaaPerson.class);
//        map.put(NormalExcelConstants.PARAMS, params);
//        map.put(NormalExcelConstants.FILE_NAME, "病人信息");
//        PoiBaseView.render(map, request, response, NormalExcelConstants.EASYPOI_EXCEL_VIEW);

        try {
            OutputStream outputStream = response.getOutputStream();
            response.reset();
            //设置ContentType字段告知浏览器返回内容类型
            response.setContentType("application/octet-stream");
            //设置Header字段


            response.setHeader("Content-Disposition", "attachment;filename=" +
                    URLEncoder.encode(new Date().getTime() + ".xlsx", StandardCharsets.UTF_8));
            //下面这行代码必须加，否则前端获取不到Content-Disposition字段，即无法获取文件名
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            //将读取的文件流复制到response的输出流中
            excelBaaPersonInfo.createExcelNet().write(outputStream);
            //刷新输出流
            outputStream.flush();
            //关闭输出流
            outputStream.close();
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "false";
    }
}
