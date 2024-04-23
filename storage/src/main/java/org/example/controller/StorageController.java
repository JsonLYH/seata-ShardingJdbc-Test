package org.example.controller;

import io.seata.core.exception.TransactionException;
import org.example.service.StorageService;
import org.example.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/storage")
public class StorageController {
    @Autowired
    StorageService storageService;
    @GetMapping("deduct")
    public ResultUtils deduct(@RequestParam Long commodityCode,@RequestParam Long count) throws TransactionException {
        storageService.deduct(commodityCode,count);
        return ResultUtils.success("库存扣减成功",null);
    }
}
