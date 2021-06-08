package com.test.service;

import com.test.pojo.JsonData;
import com.test.pojo.Merchant;

/**
 * 每个Service对应前端一个页面
 * 一个页面中需要实现的业务逻辑都在这里定义
 * 所以，我们建议，将这些方法的名称命名为与业务相关的名称
 */

public interface MerchantService {
  public JsonData select(Merchant obj);//查询业务
  public JsonData insert(Merchant obj);//添加业务
  public JsonData update(Merchant obj);//修改业务
  public JsonData delete(Merchant obj);//删除业务
}