package com.lowt.baabackend.utils.excel;

import com.lowt.baabackend.entity.BaaGh;
import com.lowt.baabackend.entity.BaaPerson;
import com.lowt.baabackend.service.BaaGhService;
import com.lowt.baabackend.service.BaaPersonService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Component
public class ExcelBaaPersonInfo {

    @Autowired
    BaaPersonService baaPersonService;

    @Autowired
    BaaGhService baaGhService;

    private static ExcelBaaPersonInfo excelBaaPersonInfo;

    @PostConstruct
    public void init() {
        excelBaaPersonInfo = this;
        excelBaaPersonInfo.baaPersonService = this.baaPersonService;
    }


    public void createTestExcel() throws IOException {
        // 获取桌面路径
        FileSystemView fsv = FileSystemView.getFileSystemView();
        String desktop = fsv.getHomeDirectory().getPath();
        String filePath = desktop + "/template.xlsx";
        File file = new File(filePath);
        OutputStream outputStream = new FileOutputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        XSSFRow row = sheet.createRow(0);
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("Sheet1");
//        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("订单号");
        row.createCell(2).setCellValue("下单时间");
        row.createCell(3).setCellValue("个数");
        row.createCell(4).setCellValue("单价");
        row.createCell(5).setCellValue("订单金额");
        row.setHeightInPoints(30); // 设置行的高度


        XSSFRow row1 = sheet.createRow(1);
//        HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("NO00001");

        // 日期格式化
        XSSFCellStyle cellStyle2 = workbook.createCellStyle();
        XSSFCreationHelper creationHelper = workbook.getCreationHelper();
//        HSSFCellStyle cellStyle2 = workbook.createCellStyle();
//        HSSFCreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle2.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        sheet.setColumnWidth(2, 20 * 256); // 设置列的宽度

        XSSFCell cell2 = row1.createCell(2);
//        HSSFCell cell2 = row1.createCell(2);
        cell2.setCellStyle(cellStyle2);
        cell2.setCellValue(new Date());

        row1.createCell(3).setCellValue(2);

        // 保留两位小数
        XSSFCellStyle cellStyle3 = workbook.createCellStyle();
        XSSFCell cell4 = row1.createCell(4);
//        HSSFCellStyle cellStyle3 = workbook.createCellStyle();
//        cellStyle3.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
//        HSSFCell cell4 = row1.createCell(4);
        cell4.setCellStyle(cellStyle3);
        cell4.setCellValue(29.5);


        // 货币格式化
        XSSFCellStyle cellStyle4 = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
//        HSSFCellStyle cellStyle4 = workbook.createCellStyle();
//        HSSFFont font = workbook.createFont();
        font.setFontName("华文行楷");
        font.setFontHeightInPoints((short) 15);
        cellStyle4.setFont(font);

        XSSFCell cell5 = row1.createCell(5);
//        HSSFCell cell5 = row1.createCell(5);
        cell5.setCellFormula("D2*E2");  // 设置计算公式

        // 获取计算公式的值
        XSSFFormulaEvaluator e = new XSSFFormulaEvaluator(workbook);
        cell5 = e.evaluateInCell(cell5);
//        HSSFFormulaEvaluator e = new HSSFFormulaEvaluator(workbook);
//        cell5 = e.evaluateInCell(cell5);
        System.out.println(cell5.getNumericCellValue());


        workbook.setActiveSheet(0);
        workbook.write(outputStream);
        outputStream.close();
    }

//    public void createExcelLocal() throws IOException {
//        // 获取桌面路径
//        FileSystemView fsv = FileSystemView.getFileSystemView();
//        String desktop = fsv.getHomeDirectory().getPath();
//        String filePath = desktop + "/template1.xlsx";
//        File file = new File(filePath);
//        OutputStream outputStream = new FileOutputStream(file);
//
//        int currentRow = -1;
//        int currentColumn = -1;
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("Sheet1");
//        XSSFRow row = sheet.createRow(++currentRow);
//
//        row.createCell(++currentColumn).setCellValue("id");
//        row.createCell(++currentColumn).setCellValue("姓名");
//        row.createCell(++currentColumn).setCellValue("性别");
//        row.createCell(++currentColumn).setCellValue("用药时间");
//        row.createCell(++currentColumn).setCellValue("出生日期");
//        row.createCell(++currentColumn).setCellValue("适应症类型");
//        row.createCell(++currentColumn).setCellValue("父亲身高");
//        row.createCell(++currentColumn).setCellValue("母亲身高");
//        row.createCell(++currentColumn).setCellValue("初始身高（出生身高）");
//        row.createCell(++currentColumn).setCellValue("初始体重（出生体重）");
//        row.createCell(++currentColumn).setCellValue("IGF1量");
//        row.createCell(++currentColumn).setCellValue("IGFBP3量");
//        row.createCell(++currentColumn).setCellValue("地址");
//        row.createCell(++currentColumn).setCellValue("电话");
//        row.createCell(++currentColumn).setCellValue("邮箱");
//        row.createCell(++currentColumn).setCellValue("身份证号码");
//        row.createCell(++currentColumn).setCellValue("生长速度");
//        row.createCell(++currentColumn).setCellValue("日期");
//        row.setHeightInPoints(20); // 设置行的高度
//
//        List<BaaPerson> baaPersonList = excelBaaPersonInfo.baaPersonService.list();
//        XSSFCellStyle cellStyleTime = workbook.createCellStyle();
//        XSSFCreationHelper creationHelper = workbook.getCreationHelper();
//        cellStyleTime.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//
//
//        System.out.println(baaPersonList);
//        for (BaaPerson person : baaPersonList) {
//
//            int startRow = currentRow + 1;
//            currentColumn = -1;
//            XSSFRow row1 = sheet.createRow(++currentRow);
//            row1.createCell(++currentColumn).setCellValue(person.getId());
//            row1.createCell(++currentColumn).setCellValue(person.getName());
//            row1.createCell(++currentColumn).setCellValue(person.getGender() == 1 ? "男" : "女");
//
//            XSSFCell xssfCell = row1.createCell(++currentColumn);
//            xssfCell.setCellStyle(cellStyleTime);
//            xssfCell.setCellValue(person.getMedicationTime());
//
//            XSSFCell xssfCell1 = row1.createCell(++currentColumn);
//            xssfCell1.setCellStyle(cellStyleTime);
//            xssfCell1.setCellValue(person.getBirthTime());
//
//            row1.createCell(++currentColumn).setCellValue(person.getIndications());
//            row1.createCell(++currentColumn).setCellValue(person.getFatherHeight() == null ? 0.0 : person.getFatherHeight());
//            row1.createCell(++currentColumn).setCellValue(person.getMotherHeight() == null ? 0.0 : person.getMotherHeight());
//            row1.createCell(++currentColumn).setCellValue(person.getInitHeight() == null ? 0.0 : person.getInitHeight());
//            row1.createCell(++currentColumn).setCellValue(person.getInitWeight() == null ? 0.0 : person.getInitHeight());
//            row1.createCell(++currentColumn).setCellValue(person.getInitIgf1() == null ? 0.0 : person.getInitIgf1());
//            row1.createCell(++currentColumn).setCellValue(person.getInitIgfbp3() == null ? 0.0 : person.getInitIgfbp3());
//            row1.createCell(++currentColumn).setCellValue(person.getAddress() == null ? " " : person.getAddress());
//            row1.createCell(++currentColumn).setCellValue(person.getTelephoneNum() == null ? " " : person.getTelephoneNum());
//            row1.createCell(++currentColumn).setCellValue(person.getEmail() == null ? " " : person.getEmail());
//            row1.createCell(++currentColumn).setCellValue(person.getIDcard() == null ? " " : person.getIDcard());
//
//            int nowColumn = currentColumn;
//            HashSet<Integer> unmergeColumn = new HashSet<>();
//            List<BaaGh> baaGhs = excelBaaPersonInfo.baaGhService.personGhList(person.getId());
//            if (!CollectionUtils.isEmpty(baaGhs)) {
//                for (BaaGh gh : baaGhs) {
//                    nowColumn = currentColumn;
//                    XSSFRow row2 = sheet.createRow(++currentRow);
//                    row2.createCell(++nowColumn).setCellValue(gh.getGh());
//                    unmergeColumn.add(nowColumn);
////                    row2.createCell(++nowColumn).setCellValue(gh.getCreateTime());
//                    XSSFCell xssfCell2 = row1.createCell(++nowColumn);
//                    xssfCell2.setCellStyle(cellStyleTime);
//                    xssfCell2.setCellValue(gh.getCreateTime());
//                    unmergeColumn.add(nowColumn);
//                }
//                currentColumn = nowColumn;
//            }
//
//            for (int i = 0; i < currentColumn; i++) {
//                // 仅仅为1行
//                if (startRow == currentRow) {
//                    continue;
//                }
//                if (!unmergeColumn.contains(i)) {
//                    sheet.addMergedRegion(new CellRangeAddress(startRow, currentRow, i, i));
//                }
//            }
//        }
//
//        workbook.setActiveSheet(0);
//        workbook.write(outputStream);
//        outputStream.close();
//    }


    public XSSFWorkbook createExcelNet() throws IOException {

        int currentRow = -1;
        int currentColumn = -1;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet1");
        XSSFRow row = sheet.createRow(++currentRow);

        row.createCell(++currentColumn).setCellValue("id");
        row.createCell(++currentColumn).setCellValue("姓名");
        row.createCell(++currentColumn).setCellValue("性别");
        row.createCell(++currentColumn).setCellValue("用药时间");
        row.createCell(++currentColumn).setCellValue("出生日期");
        row.createCell(++currentColumn).setCellValue("适应症类型");
        row.createCell(++currentColumn).setCellValue("父亲身高");
        row.createCell(++currentColumn).setCellValue("母亲身高");
        row.createCell(++currentColumn).setCellValue("初始身高（出生身高）");
        row.createCell(++currentColumn).setCellValue("初始体重（出生体重）");
        row.createCell(++currentColumn).setCellValue("IGF1量");
        row.createCell(++currentColumn).setCellValue("IGFBP3量");
        row.createCell(++currentColumn).setCellValue("地址");
        row.createCell(++currentColumn).setCellValue("电话");
        row.createCell(++currentColumn).setCellValue("邮箱");
        row.createCell(++currentColumn).setCellValue("身份证号码");
        row.createCell(++currentColumn).setCellValue("生长速度");
        row.createCell(++currentColumn).setCellValue("日期");
        row.setHeightInPoints(20); // 设置行的高度

        List<BaaPerson> baaPersonList = excelBaaPersonInfo.baaPersonService.list();
        XSSFCellStyle cellStyleTime = workbook.createCellStyle();
        XSSFCreationHelper creationHelper = workbook.getCreationHelper();
        cellStyleTime.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));


        System.out.println(baaPersonList);
        for (BaaPerson person : baaPersonList) {

            int startRow = currentRow + 1;
            currentColumn = -1;
            XSSFRow row1 = sheet.createRow(++currentRow);
            row1.createCell(++currentColumn).setCellValue(person.getId());
            row1.createCell(++currentColumn).setCellValue(person.getName());
            row1.createCell(++currentColumn).setCellValue(person.getGender() == 1 ? "男" : "女");

            XSSFCell xssfCell = row1.createCell(++currentColumn);
            xssfCell.setCellStyle(cellStyleTime);
            xssfCell.setCellValue(person.getMedicationTime());

            XSSFCell xssfCell1 = row1.createCell(++currentColumn);
            xssfCell1.setCellStyle(cellStyleTime);
            xssfCell1.setCellValue(person.getBirthTime());

            row1.createCell(++currentColumn).setCellValue(person.getIndications());
            row1.createCell(++currentColumn).setCellValue(person.getFatherHeight() == null ? 0.0 : person.getFatherHeight());
            row1.createCell(++currentColumn).setCellValue(person.getMotherHeight() == null ? 0.0 : person.getMotherHeight());
            row1.createCell(++currentColumn).setCellValue(person.getInitHeight() == null ? 0.0 : person.getInitHeight());
            row1.createCell(++currentColumn).setCellValue(person.getInitWeight() == null ? 0.0 : person.getInitHeight());
            row1.createCell(++currentColumn).setCellValue(person.getInitIgf1() == null ? 0.0 : person.getInitIgf1());
            row1.createCell(++currentColumn).setCellValue(person.getInitIgfbp3() == null ? 0.0 : person.getInitIgfbp3());
            row1.createCell(++currentColumn).setCellValue(person.getAddress() == null ? " " : person.getAddress());
            row1.createCell(++currentColumn).setCellValue(person.getTelephoneNum() == null ? " " : person.getTelephoneNum());
            row1.createCell(++currentColumn).setCellValue(person.getEmail() == null ? " " : person.getEmail());
            row1.createCell(++currentColumn).setCellValue(person.getIDcard() == null ? " " : person.getIDcard());

            int nowColumn = currentColumn;
            HashSet<Integer> unmergeColumn = new HashSet<>();
            List<BaaGh> baaGhs = excelBaaPersonInfo.baaGhService.personGhList(person.getId());
            if (!CollectionUtils.isEmpty(baaGhs)) {
                for (BaaGh gh : baaGhs) {
                    nowColumn = currentColumn;
                    XSSFRow row2 = sheet.createRow(++currentRow);
                    row2.createCell(++nowColumn).setCellValue(gh.getGh());
                    unmergeColumn.add(nowColumn);
//                    row2.createCell(++nowColumn).setCellValue(gh.getCreateTime());
                    XSSFCell xssfCell2 = row2.createCell(++nowColumn);
                    xssfCell2.setCellStyle(cellStyleTime);
                    xssfCell2.setCellValue(gh.getCreateTime());
                    unmergeColumn.add(nowColumn);
                }
                currentColumn = nowColumn;
            }

            for (int i = 0; i < currentColumn; i++) {
                // 仅仅为1行
                if (startRow == currentRow) {
                    continue;
                }
                if (!unmergeColumn.contains(i)) {
                    sheet.addMergedRegion(new CellRangeAddress(startRow, currentRow, i, i));
                }
            }
        }

        workbook.setActiveSheet(0);

        return workbook;
    }


}
