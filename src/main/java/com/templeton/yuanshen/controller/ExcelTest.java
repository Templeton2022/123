package com.templeton.yuanshen.controller;

import com.templeton.yuanshen.pojo.ExcelBean;
import com.templeton.yuanshen.utils.ExcelProUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@CrossOrigin
@Controller
public class ExcelTest {

    @Autowired
    ExcelProUtil excelProUtil;

    @GetMapping("/api/exceltest")
    @ResponseBody
    public void excelPro(HttpServletResponse response){
        ExcelBean excelBean = new ExcelBean();
        List<String> firstName = new LinkedList<>();
        firstName.add("姓名");
        firstName.add("性别");
        firstName.add("年龄");
        firstName.add("班级");
        List<String[]> rowData = new LinkedList<>();
        String[] stuStr1 = {"张三","男","19","Java二班"};
        String[] stuStr2 = {"李四","女","20","三班"};
        rowData.add(stuStr1);
        rowData.add(stuStr2);
        excelBean.setExcelName("学生1");
        excelBean.setFirstRowName(firstName);
        excelBean.setRowData(rowData);
        excelBean.setSheetName("信息表");
        excelProUtil.excelsProducer(excelBean,response);
    }
}
