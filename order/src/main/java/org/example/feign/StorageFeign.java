package org.example.feign;

import org.example.utils.ResultUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("storage")
public interface StorageFeign {
    @GetMapping(value = "/api/storage/deduct")
    ResultUtils deduct(@RequestParam(value = "commodityCode") String commodityCode,@RequestParam(value = "count") Long count);
}
