package com.my.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.asset.entity.Asset;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssetMapper extends BaseMapper<Asset> {
}
