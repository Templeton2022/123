package com.templeton.yuanshen.utils;

import com.templeton.yuanshen.pojo.TalentDTO;

public class TalentUtil {
    /**
     * 校验TalentDTO参数不为空
     * @param talentDTO
     * @return true: 有参数为空
     */
    public static boolean talentIsNull(TalentDTO talentDTO){
        boolean isNull = talentDTO.getA_start() <= 0 || talentDTO.getA_end() <= 0 ||
                talentDTO.getE_start() <= 0 || talentDTO.getE_end() <= 0 ||
                talentDTO.getQ_start() <= 0 || talentDTO.getQ_end() <= 0;
        return isNull;
    }

    /**
     * 校验TalentDTO参数 是否符合规则
     * @param talentDTO
     * @return true: TalentDTO的所有参数合法
     */
    public static boolean talentIsRule(TalentDTO talentDTO){
        // 验证天赋等级是否在 1~10 范围内
        boolean talentIsRange = isRange(talentDTO.getA_start()) && isRange(talentDTO.getA_end()) &&
                isRange(talentDTO.getE_start()) && isRange(talentDTO.getE_end()) &&
                isRange(talentDTO.getQ_start()) && isRange(talentDTO.getQ_end());

        // 验证天赋等级 是否 end >= start
        boolean endGreaterthanstart = talentDTO.getA_end() >= talentDTO.getA_start() &&
                talentDTO.getE_end() >= talentDTO.getE_start() &&
                talentDTO.getQ_end() >= talentDTO.getQ_start();

        if (talentIsRange && endGreaterthanstart){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 校验天赋等级是否为 1~10 级
     * @param level
     * @return true: 传入的等级在 1~10 级内
     */
    private static boolean isRange(int level){
        if (1 <= level && level <= 10){
            return true;
        }else {
            return false;
        }
    }
}
