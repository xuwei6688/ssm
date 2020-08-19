package com.xu.mapper;

import com.xu.pojo.Account;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AccountMapper {

    //  定义dao层接口方法--> 查询account表所有数据
    List<Account> queryAccountList();

    Account selectById(Integer id);

}
