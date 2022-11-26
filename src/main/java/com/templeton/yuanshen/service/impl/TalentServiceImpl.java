package com.templeton.yuanshen.service.impl;

import com.templeton.yuanshen.dao.TalentDAO;
import com.templeton.yuanshen.pojo.ExcelBean;
import com.templeton.yuanshen.pojo.TalentBO;
import com.templeton.yuanshen.pojo.TalentDTO;
import com.templeton.yuanshen.pojo.TalentExcelDTO;
import com.templeton.yuanshen.service.TalentService;
import com.templeton.yuanshen.utils.ExcelProUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;

@Service("TalentService")
public class TalentServiceImpl implements TalentService {

    @Autowired
    private TalentDAO talentDAO;
    @Autowired
    private ExcelProUtil excelProUtil;

    @Override
    public TalentBO talenttotal(TalentDTO reqTalentDTO) {
        // 查找最大等级
        int maxTalentLevel = maxNumber(reqTalentDTO);
        // 获取 1 ~ maxlevel 消耗的天赋材料对象列表
        List<TalentBO> listTalentBO = talentDAO.listTalentBO(maxTalentLevel);
        // 获取天赋
        int a_start = reqTalentDTO.getA_start();
        int a_end = reqTalentDTO.getA_end();
        int e_start = reqTalentDTO.getE_start();
        int e_end = reqTalentDTO.getE_end();
        int q_start = reqTalentDTO.getQ_start();
        int q_end = reqTalentDTO.getQ_end();
        TalentBO talent_info_need = new TalentBO();

        if (a_end != 1){
            while (a_start < a_end){
                a_start += 1;
                talent_info_need = talentSum(talent_info_need, listTalentBO.get(a_start - 1));
            }
        }

        if (e_end != 1){
            while (e_start < e_end){
                e_start += 1;
                talent_info_need = talentSum(talent_info_need, listTalentBO.get(e_start - 1));
            }
        }

        if (q_end != 1){
            while (q_start < q_end){
                q_start += 1;
                talent_info_need = talentSum(talent_info_need, listTalentBO.get(q_start - 1));
            }
        }

        return talent_info_need;
    }

    @Override
    public void talentSaveExcel(TalentExcelDTO reqTalentDTO, HttpServletResponse response) {
        ExcelBean excelBean = new ExcelBean();
        List<String> firstName = new LinkedList<>();
        firstName.add("技能类别");
        firstName.add("普通攻击");
        firstName.add("元素战技");
        firstName.add("元素爆发");
        List<String[]> rowData = new LinkedList<>();
        String[] row1 = {"技能等级",reqTalentDTO.getTalentDTO().getA_start() + "→" +reqTalentDTO.getTalentDTO().getA_end()
                , reqTalentDTO.getTalentDTO().getE_start() + "→" +reqTalentDTO.getTalentDTO().getE_end()
                , reqTalentDTO.getTalentDTO().getQ_start() + "→" +reqTalentDTO.getTalentDTO().getQ_end()};
        String[] row2 = {"材料名称", "绿色天赋书", "蓝色天赋书", "紫色天赋书", "灰色掉落物", "绿色掉落物", "蓝色掉落物", "周本材料"
                , "皇冠", "摩拉"};
        String[] row3 = {"需要数量", reqTalentDTO.getTalentBO().getGreen_talentbook() + "", reqTalentDTO.getTalentBO().getBlue_talentbook() + ""
                , reqTalentDTO.getTalentBO().getPurple_talentbook() + "", reqTalentDTO.getTalentBO().getGrey_monsterdrop() + ""
                , reqTalentDTO.getTalentBO().getGreen_monsterdrop() + "", reqTalentDTO.getTalentBO().getBlue_monsterdrop() + ""
                , reqTalentDTO.getTalentBO().getWeek_drop() + "", reqTalentDTO.getTalentBO().getCrown() + ""
                , reqTalentDTO.getTalentBO().getMora() + ""};
        rowData.add(row1);
        rowData.add(row2);
        rowData.add(row3);
        excelBean.setExcelName(reqTalentDTO.getExcelName());
        excelBean.setFirstRowName(firstName);
        excelBean.setRowData(rowData);
        excelBean.setSheetName("信息表");
        excelProUtil.excelsProducer(excelBean,response);
    }

    // 面对对象算法
    private  TalentBO talentSum(TalentBO talentBOA, TalentBO talentBOB){
        TalentBO talentBO = new TalentBO();
        talentBO.setGreen_talentbook(talentBOA.getGreen_talentbook() + talentBOB.getGreen_talentbook());
        talentBO.setBlue_talentbook(talentBOA.getBlue_talentbook() + talentBOB.getBlue_talentbook());
        talentBO.setPurple_talentbook(talentBOA.getPurple_talentbook() + talentBOB.getPurple_talentbook());

        talentBO.setGrey_monsterdrop(talentBOA.getGrey_monsterdrop() + talentBOB.getGrey_monsterdrop());
        talentBO.setGreen_monsterdrop(talentBOA.getGreen_monsterdrop() + talentBOB.getGreen_monsterdrop());
        talentBO.setBlue_monsterdrop(talentBOA.getBlue_monsterdrop() + talentBOB.getBlue_monsterdrop());

        talentBO.setWeek_drop(talentBOA.getWeek_drop() + talentBOB.getWeek_drop());
        talentBO.setCrown(talentBOA.getCrown() + talentBOB.getCrown());
        talentBO.setMora(talentBOA.getMora() + talentBOB.getMora());
        return talentBO;
    }

    private int maxNumber(TalentDTO talentDTO){
        int max = 0;
        int[] arrayTalentDTO = {talentDTO.getA_start() ,talentDTO.getA_end(), talentDTO.getE_start(),
                talentDTO.getE_end(), talentDTO.getQ_start(), talentDTO.getQ_end()};
        for (int i = 0 ;i < 6; i++){
            if (arrayTalentDTO[i] >= max){
                max = arrayTalentDTO[i];
            }
        }
        return max;
    }
}
