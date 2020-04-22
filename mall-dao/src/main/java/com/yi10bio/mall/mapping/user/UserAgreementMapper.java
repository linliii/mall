package com.yi10bio.mall.mapping.user;

import com.yi10bio.mall.model.user.UserAgreement;

public interface UserAgreementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAgreement record);

    int insertSelective(UserAgreement record);

    UserAgreement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAgreement record);

    int updateByPrimaryKey(UserAgreement record);
}