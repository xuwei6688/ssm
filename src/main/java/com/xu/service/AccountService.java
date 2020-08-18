package com.xu.service;


import com.xu.pojo.Account;

import java.util.List;

public interface AccountService {
    List<Account> queryAccountList() throws Exception;
}
