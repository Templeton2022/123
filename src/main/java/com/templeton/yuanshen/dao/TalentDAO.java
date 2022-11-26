package com.templeton.yuanshen.dao;

import com.templeton.yuanshen.pojo.TalentBO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TalentDAO {
    /**
     *  通过最大等级查找天赋等级信息
     *
     */
    List<TalentBO> listTalentBO(int maxTalentLevel);
}
