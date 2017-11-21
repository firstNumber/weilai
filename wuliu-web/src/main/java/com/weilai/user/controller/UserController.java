package com.weilai.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weilai.user.account.service.CarboxService;
import com.weilai.user.service.UserService;



@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	CarboxService carboxService;
	@RequestMapping(value = "queryUser.action", method = RequestMethod.GET)
	@ResponseBody
	public String querUser() {
		System.out.println("啦啦啦啦============================");
		carboxService.queryCar();
		return "index";
	}
}
