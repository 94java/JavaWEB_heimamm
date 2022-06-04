package top.hellocode.service.store;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import top.hellocode.domain.store.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HelloCode
 * @site https://www.hellocode.top
 * @date 2022年05月29日 14:09
 */
public class PoiTest {

    @Test
    public void testWriteByPoi() throws IOException {
        // 1. 获取到对应的Excel文件——工作簿文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 2. 创建工作表
        XSSFSheet sheet = wb.createSheet();
        wb.createSheet("工作表名称");     // 指定名称
        // 3. 创建工作表中的行对象
        XSSFRow row = sheet.createRow(1);       // 第二行（索引从0开始）
        // 4. 创建工作表中行中的列对象
        XSSFCell cell = row.createCell(1);      // 第二列
        // 5. 为单元格设置数据
        cell.setCellValue("测试一下单元格");

        // 创建一个文件对象,作为excel文件内容的输出文件
        File f = new File("test.xlsx");
        // 输出时通过流的形式对外输出，包装对应的目标文件
        OutputStream os = new FileOutputStream(f);
        // 将内存中的workbook数据写入到流中
        wb.write(os);
        // 释放资源
        os.close();
        wb.close();
    }

    @Test
    public void testReadByPoi() throws IOException {
        // 1. 获取要读取的文件工作簿对象
        Workbook wb = new XSSFWorkbook("test.xlsx");
        // 2. 获取工作表
        Sheet sheet = wb.getSheetAt(0);
        // 3. 获取行
        Row row = sheet.getRow(1);
        // 4. 获取单元格
        Cell cell = row.getCell(1);
        // 5. 根据数据类型获取数据
        String data = cell.getStringCellValue();
        System.out.println(data);
        // 释放资源
        wb.close();
    }

    @Test
    public void testProjectPoi() throws IOException {
        // 1. 获取到对应的Excel文件——工作簿文件
        XSSFWorkbook wb = new XSSFWorkbook();
        // 2. 创建工作表
        XSSFSheet s = wb.createSheet("题目数据文件");

        // 设置通用配置
//        s.setColumnWidth(4,100);        // 列宽
        // 设置水平居中
        CellStyle cs_field = wb.createCellStyle();
        cs_field.setAlignment(HorizontalAlignment.CENTER);
        // 表格线
        cs_field.setBorderTop(BorderStyle.THIN);
        cs_field.setBorderBottom(BorderStyle.THIN);
        cs_field.setBorderLeft(BorderStyle.THIN);
        cs_field.setBorderRight(BorderStyle.THIN);

        // 制作标题
        s.addMergedRegion(new CellRangeAddress(1,1,1,12));

        Row row_1 = s.createRow(1);
        Cell cell_1_1 = row_1.createCell(1);
        cell_1_1.setCellValue("在线试题导出信息");
        // 创建一个样式
        CellStyle cs_title = wb.createCellStyle();
        // 设置水平和居中对齐
        cs_title.setAlignment(HorizontalAlignment.CENTER);
        cs_title.setVerticalAlignment(VerticalAlignment.CENTER);
        // 为单元格添加样式
        cell_1_1.setCellStyle(cs_title);

        // 制作表头
        String[] fields = {"题目ID","所属公司ID","所属目录ID","题目简介","题干描述",
                "题干配图","题目分析","题目类型","题目难度","是否经典题","题目状态","审核状态"};
        XSSFRow row_2 = s.createRow(2);

        for (int i = 0; i < fields.length; i++) {
            Cell cell_2_temp =  row_2.createCell(1 + i);
            cell_2_temp.setCellValue(fields[i]);
            cell_2_temp.setCellStyle(cs_field);
        }


        // 制作数据区
        List<Question> questionList = new ArrayList<>();
        Question qq = new Question();
        qq.setId("11");
        qq.setPicture("12");
        qq.setReviewStatus("13");
        qq.setAnalysis("14");
        qq.setCatalogId("15");
        qq.setCompanyId("16");
        qq.setDifficulty("17");
        qq.setIsClassic("18");
        qq.setRemark("19");
        qq.setState("110");
        qq.setSubject("111");
        qq.setType("112");
        questionList.add(qq);

        Question qqq = new Question();
        qqq.setId("11");
        qqq.setPicture("12");
        qqq.setReviewStatus("13");
        qqq.setAnalysis("14");
        qqq.setCatalogId("15");
        qqq.setCompanyId("16");
        qqq.setDifficulty("17");
        qqq.setIsClassic("18");
        qqq.setRemark("19");
        qqq.setState("110");
        qqq.setSubject("111");
        qqq.setType("112");
        questionList.add(qqq);


        int row_index = 0;
        for (Question q : questionList) {
            int cell_index = 0;
            XSSFRow row_temp = s.createRow(3 + row_index++);
            Cell cell_data_1 =  row_temp.createCell(1 + cell_index++);
            cell_data_1.setCellValue(q.getId());
            cell_data_1.setCellStyle(cs_field);

            Cell cell_data_2 =  row_temp.createCell(1 + cell_index++);
            cell_data_2.setCellValue(q.getCompanyId());
            cell_data_2.setCellStyle(cs_field);

            Cell cell_data_3 =  row_temp.createCell(1 + cell_index++);
            cell_data_3.setCellValue(q.getCatalogId());
            cell_data_3.setCellStyle(cs_field);

            Cell cell_data_4 =  row_temp.createCell(1 + cell_index++);
            cell_data_4.setCellValue(q.getRemark());
            cell_data_4.setCellStyle(cs_field);

            Cell cell_data_5 =  row_temp.createCell(1 + cell_index++);
            cell_data_5.setCellValue(q.getSubject());
            cell_data_5.setCellStyle(cs_field);

            Cell cell_data_6 =  row_temp.createCell(1 + cell_index++);
            cell_data_6.setCellValue(q.getPicture());
            cell_data_6.setCellStyle(cs_field);

            Cell cell_data_7 =  row_temp.createCell(1 + cell_index++);
            cell_data_7.setCellValue(q.getAnalysis());
            cell_data_7.setCellStyle(cs_field);

            Cell cell_data_8 =  row_temp.createCell(1 + cell_index++);
            cell_data_8.setCellValue(q.getType());
            cell_data_8.setCellStyle(cs_field);

            Cell cell_data_9 =  row_temp.createCell(1 + cell_index++);
            cell_data_9.setCellValue(q.getDifficulty());
            cell_data_9.setCellStyle(cs_field);

            Cell cell_data_10 =  row_temp.createCell(1 + cell_index++);
            cell_data_10.setCellValue(q.getIsClassic());
            cell_data_10.setCellStyle(cs_field);

            Cell cell_data_11 =  row_temp.createCell(1 + cell_index++);
            cell_data_11.setCellValue(q.getState());
            cell_data_11.setCellStyle(cs_field);

            Cell cell_data_12 =  row_temp.createCell(1 + cell_index++);
            cell_data_12.setCellValue(q.getReviewStatus());
            cell_data_12.setCellStyle(cs_field);
        }




        // 创建一个文件对象,作为excel文件内容的输出文件
        File f = new File("test.xlsx");
        // 输出时通过流的形式对外输出，包装对应的目标文件
        OutputStream os = new FileOutputStream(f);
        // 将内存中的workbook数据写入到流中
        wb.write(os);
        // 释放资源
        os.close();
        wb.close();
    }
}
