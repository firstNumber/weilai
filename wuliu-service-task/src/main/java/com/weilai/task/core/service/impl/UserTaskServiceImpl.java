package com.weilai.task.core.service.impl;

import com.weilai.common.util.SpringContextUtil;
import com.weilai.task.common.QuartzManager;
import com.weilai.task.core.common.ScheduleJob;
import com.weilai.task.core.service.UserTaskService;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liyongzhen
 * @create 2019-08-19 11:30
 **/
@Service("userTaskService")
public class UserTaskServiceImpl implements UserTaskService {
	public void pushUser() {
		System.out.println("我是一個Task");
	};

	@Override
	public void funJob() throws SchedulerException {
		updateJob();
	}

	public String invoke(String serviceName, String mothedName) {
		try {
			Object service = SpringContextUtil.getBean(serviceName);
			java.lang.reflect.Method m = service.getClass().getMethod(mothedName);
			m.invoke(service);
			return "执行成功!";
		} catch (Exception e) {
			return "执行失败:" + e.getMessage();
		}

	}

	public List<ScheduleJob> jobList() throws SchedulerException {
		QuartzManager quartzManager = new QuartzManager();
		return quartzManager.getAllJob();
	}

	public void updateJob() throws SchedulerException {
		QuartzManager quartzManager = new QuartzManager();
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobName("pushUserTrigger");
		scheduleJob.setJobGroup("DEFAULT");
		scheduleJob.setCronExpression("*/10 * * * * ?");
		quartzManager.updateJobCron(scheduleJob);
	}
}
