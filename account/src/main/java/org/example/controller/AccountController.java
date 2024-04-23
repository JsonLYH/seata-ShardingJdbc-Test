package org.example.controller;

import org.example.model.Account;
import org.example.service.impl.AccountServiceImpl;
import org.example.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;
    @GetMapping("debit")
    public ResultUtils debit(@RequestParam String userId,@RequestParam Long money){
        accountService.debit(userId,money);
        return ResultUtils.success("扣减成功",null);
    }

    @GetMapping("getMoney")
    public Long debit(@RequestParam String userId){
        Long money = accountService.getMoney(userId);
        return money;
    }
}
