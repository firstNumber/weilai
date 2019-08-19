
import java.util.List;

import com.weilai.task.core.common.ScheduleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.weilai.task.core.service.UserTaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context.xml" })
public class TaskTest {
	@Autowired
	private UserTaskService userTaskService;

	@Test
	public void userSettlement() {
		try {
			String invoke = userTaskService.invoke("userTaskService", "pushUser");
			System.out.println(invoke);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testTask() throws SchedulerException {
		List<ScheduleJob> list = userTaskService.jobList();
		for (ScheduleJob job : list) {
			System.out.println(job);
		}

	}

	@Test
	public void testjob() throws SchedulerException {
		userTaskService.funJob();

	}

}
