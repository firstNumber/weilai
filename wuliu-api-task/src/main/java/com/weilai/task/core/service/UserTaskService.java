package com.weilai.task.core.service;

import com.weilai.task.core.common.ScheduleJob;
import org.quartz.SchedulerException;

import java.util.List;

public interface UserTaskService {
	public void funJob() throws SchedulerException;

	public String invoke(String serviceName, String mothedName);

	public List<ScheduleJob> jobList() throws SchedulerException;
}
