package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.Storage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StorageMapper {

    Storage selectById(@Param("id") Integer id);

    Storage findByCommodityCode(@Param("commodityCode") Long commodityCode);

    int updateById(Storage record);

    void insert(Storage record);

    void insertBatch(List<Storage> records);

    int updateBatch(@Param("list") List<Long> ids, @Param("commodityCode") String commodityCode);
}