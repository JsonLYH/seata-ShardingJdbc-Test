package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.Account;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper extends BaseMapper<Account> {
    Account selectByUserId(@Param("userId") String userId);
    int updateById(Account record);
}
