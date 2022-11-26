package com.templeton.yuanshen.service;

import com.templeton.yuanshen.pojo.TalentBO;
import com.templeton.yuanshen.pojo.TalentDTO;
import com.templeton.yuanshen.pojo.TalentExcelDTO;

import javax.servlet.http.HttpServletResponse;

public interface TalentService {
    /**
     * 计算天赋等级材料消耗
     * @param reqTalentDTO
     * @return TalentBO
     */
    TalentBO talenttotal(TalentDTO reqTalentDTO);

    /**
     * 保存文件
     * @param reqTalentDTO
     */
    void talentSaveExcel(TalentExcelDTO reqTalentDTO, HttpServletResponse response);
}
