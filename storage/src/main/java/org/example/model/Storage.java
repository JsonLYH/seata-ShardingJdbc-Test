package org.example.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("storage_tbl")
public class Storage {
    private Integer id;

    private Long commodityCode;

    private Long count;

}
