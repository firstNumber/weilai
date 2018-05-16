package com.weilai.task.core;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.weilai.common.util.SpringContextUtil;
import com.weilai.task.common.QuartzManager;
import com.weilai.task.common.ScheduleJob;

@Service("userTaskService")
public class UserTaskService {

	@Scheduled(fixedRate = 1000 * 60 * 60)
	public void pushUser() {
		System.out.println("我是一個Task");
	};

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
		scheduleJob.setCronExpression("*/30 * * * * ?");
		quartzManager.updateJobCron(scheduleJob);
	}
}
