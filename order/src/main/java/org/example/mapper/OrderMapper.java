package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.model.Order;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {
    int insert(Order record);
}
