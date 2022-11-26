package com.templeton.yuanshen.utils;

import com.templeton.yuanshen.pojo.ExcelBean;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Component
public class ExcelProUtil {
    public void excelsProducer(ExcelBean excelBean, HttpServletResponse response){
        XSSFWorkbook wb = new XSSFWorkbook();
        // 创建工作表
        XSSFSheet sheet = wb.createSheet(excelBean.getSheetName());
        // 创建第一行标题
        XSSFRow first = sheet.createRow(0);
        for (int firstRow = 0; firstRow < excelBean.getFirstRowName().size(); firstRow++) {
            first.createCell(firstRow).setCellValue(excelBean.getFirstRowName().get(firstRow));
        }
        // 每行字段名
        for (int rows = 0; rows < excelBean.getRowData().size(); rows++) {
            //从第二行开始递增
            XSSFRow excelRow = sheet.createRow(rows + 1);
            for (int i = 0; i < excelBean.getRowData().get(rows).length; i++) {
                XSSFCell cell = excelRow.createCell(i);
                cell.setCellValue(excelBean.getRowData().get(rows)[i]);
            }
        }
        // 响应给客户端
        try {
            this.setResponseHeader(response, excelBean.getExcelName() + ".xlsx");
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 发送响应流方法,无论是word，excel，text都实用的响应流类
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //浏览器检测文件类型，有两种响应：第一种是MIME（多功能Internet 邮件扩充服务，最早用于邮件系统，后来拓展到浏览器中）；
            // 另一种，当浏览器无法确定文件类型时，就是application/octet-stream类型。
//            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            //关闭缓存
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
