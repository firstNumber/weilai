package com.weilai.user.controller;

import com.weilai.task.core.service.UserTaskService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.weilai.common.cache.CacheMan;
import com.weilai.user.account.service.CarboxService;



@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	CarboxService carboxService;
	@Autowired
	UserTaskService userTaskService;
	@RequestMapping(value = "queryUser.action", method = RequestMethod.GET)
	@ResponseBody
	public String querUser() {
		String lockKey="catbox.service";
		CacheMan.postLock(lockKey);
		carboxService.queryCar();
		CacheMan.unLock(lockKey);
		return "index";
	}

	@RequestMapping(value ="updateTask.action",method =RequestMethod.POST )
	@ResponseBody
	public String updateTask(){
		try {
			userTaskService.funJob();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return  "OK";
	}
}
