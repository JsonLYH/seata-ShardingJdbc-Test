package org.example.feign;

import org.example.utils.ResultUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name="account")
public interface AccountFeign {
    @GetMapping(value = "/api/account/debit")
    ResultUtils debit(@RequestParam(value = "userId") String userId,@RequestParam(value = "money") Long money);

    @GetMapping(value = "/api/account/getMoney")
    Long getMoney(@RequestParam(value = "userId") String userId);
}
