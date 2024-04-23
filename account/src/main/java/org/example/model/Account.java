package org.example.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("account_tbl")
public class Account {
    private Integer id;

    private String userId;

    private Long money;
}
