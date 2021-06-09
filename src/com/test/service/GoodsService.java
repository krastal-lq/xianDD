package com.test.service;

import com.test.pojo.JsonData;
import com.test.pojo.Goods;

/**
 * 每个Service对应前端一个页面
 * 一个页面中需要实现的业务逻辑都在这里定义
 * 所以，我们建议，将这些方法的名称命名为与业务相关的名称
 */

public interface GoodsService {
  public JsonData select(Goods obj);//查询业务
  public JsonData selectDetail(Goods obj);//随机查询业务
  public JsonData selectRnd(Goods obj);//随机查询业务
  public JsonData insert(Goods obj);//添加业务
  public JsonData update(Goods obj);//修改业务
  public JsonData delete(Goods obj);//删除业务
}