package com.templeton.yuanshen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TalentDO {
    private int id;
    private int level;
    private int green_talentbook;
    private int blue_talentbook;
    private int purple_talentbook;
    private int grey_monsterdrop;
    private int green_monsterdrop;
    private int blue_monsterdrop;
    private int week_drop;
    private int crown;
    private int mora;
    private int creat_time;
    private int update_time;
}
