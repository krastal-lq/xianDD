package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.pojo.BaseDataPojo;
import com.test.pojo.LoginSession;
import com.test.service.LoginService;
import com.test.util.HttpUtil;
import com.google.gson.Gson;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置前后端交互参数的编码为utf-8		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// 用PrintWriter对象返回数据
		PrintWriter out = resp.getWriter();		
		// 提取前端提交的数据
		String code = req.getParameter("code");	
		String appId = "wxad9e837ae0923871";
		String appSecret = "2e8c6ce9b85317e3cf334fd7221f2cfb";
		String url = "https://api.weixin.qq.com/sns/jscode2session" + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code +
				"&grant_type=authorization_code";
		// 发送请求到微信API，获取用户的openId
		HttpUtil httpUtil = new HttpUtil();
		Map<String, String> map = new HashMap<>();
		String jsonStr = httpUtil.doGet(url, map);
		map = new Gson().fromJson(jsonStr, Map.class);
		// 获取用的openId
		String openId = map.get("openid");
//		System.out.println(openId);
		// 生成登录凭证token，这里使用时间戳，实际开发中推荐用更成熟的机制生成token
		String token = "token_" + new Date().getTime();
		// 将openId和token传递到服务层，做下一步业务处理
		LoginService loginService = new LoginService();
		// 获取用户登录信息
		BaseDataPojo<LoginSession> dataPojo = loginService.login(openId, token);
		out.print(new Gson().toJson(dataPojo));
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// GET请求统一由POST处理
		doPost(req, resp);
	}
}
