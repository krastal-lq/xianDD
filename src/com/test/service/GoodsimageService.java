package com.test.service;

import com.test.pojo.JsonData;
import com.test.pojo.Goodsimage;

/**
 * 每个Service对应前端一个页面
 * 一个页面中需要实现的业务逻辑都在这里定义
 * 所以，我们建议，将这些方法的名称命名为与业务相关的名称
 */

public interface GoodsimageService {
  public JsonData select(Goodsimage obj);//查询业务
  public JsonData insert(Goodsimage obj);//添加业务
  public JsonData update(Goodsimage obj);//修改业务
  public JsonData delete(Goodsimage obj);//删除业务
}