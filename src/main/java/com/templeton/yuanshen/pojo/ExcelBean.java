package com.templeton.yuanshen.pojo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelBean {
        //工作簿名称
        private String excelName;
        //工作表名称
        private String sheetName;
        //第一行字段名
        private List<String> firstRowName;
        //从第二个单元格开始每行的数据
        private List<String[]> rowData;
}
