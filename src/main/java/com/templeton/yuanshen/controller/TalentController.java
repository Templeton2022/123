package com.templeton.yuanshen.controller;

import com.templeton.yuanshen.pojo.TalentExcelDTO;
import com.templeton.yuanshen.utils.StringUtil;
import com.templeton.yuanshen.utils.TalentUtil;
import com.templeton.yuanshen.pojo.TalentBO;
import com.templeton.yuanshen.pojo.TalentDTO;
import com.templeton.yuanshen.result.Result;
import com.templeton.yuanshen.result.ResultFactory;
import com.templeton.yuanshen.service.TalentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@Controller
public class TalentController {

    @Autowired
    TalentService talentService;

    @PostMapping("/api/talent/talenttotal")
    @ResponseBody
    public Result talentTotal(@RequestBody TalentDTO reqTalentDTO){
        // 判断请求体参数是否为空
        boolean isNull = TalentUtil.talentIsNull(reqTalentDTO);
        boolean talentIsRule = TalentUtil.talentIsRule(reqTalentDTO);
        if (isNull){
            String message = "天赋等级请求体有参数为空！";
            return ResultFactory.Fail(message);
        }
        if (!talentIsRule){
            String message = "输入的天赋等级参数不符合规则！";
            return ResultFactory.Fail(message);
        }

        try {
            TalentBO talentBO = talentService.talenttotal(reqTalentDTO);
            return ResultFactory.Success(talentBO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultFactory.Internal_Server_Error("服务器内部错误，请联系管理员！");
        }
    }

    @PostMapping("/api/talent/saveexcel")
    @ResponseBody
    public void talentSaveExcel(@RequestBody TalentExcelDTO reqTalentExcelDTO, HttpServletResponse response){
//        if (StringUtil.isEmpty(reqTalentExcelDTO.getExcelName())){
//            String message = "未输入文件名！";
//            return ResultFactory.Fail(message);
//        }
//        if (reqTalentExcelDTO.getTalentBO() == null){
//            String message = "未上传天赋等级！";
//            return ResultFactory.Fail(message);
//        }
//        if (reqTalentExcelDTO.getTalentDTO() == null){
//            String message = "未上传所需材料合计！";
//            return ResultFactory.Fail(message);
//        }

        try {
            talentService.talentSaveExcel(reqTalentExcelDTO, response);
            String message = "成功导出Excel!";
//            return ResultFactory.Success(message);
        }catch (Exception e){
            // 已在工具类中抛出异常
//            return ResultFactory.Internal_Server_Error("服务器内部错误，请联系管理员！");
        }
    }
}
