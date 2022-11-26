package com.templeton.yuanshen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentExcelDTO {
    private String excelName;
    private TalentDTO talentDTO;
    private TalentBO talentBO;
}
