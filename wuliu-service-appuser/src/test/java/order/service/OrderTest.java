package order.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.weilai.appuser.core.service.AppUserService;
import com.weilai.appuser.core.service.OrderShortService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring/application-context.xml" })
public class OrderTest {
	OrderShortService orderShortService;
	private ApplicationContext contex;

	AppUserService appUserService;

	@org.junit.Before
	public void setUp() {
		contex = new ClassPathXmlApplicationContext("classpath:spring/application-context.xml");
		orderShortService = (OrderShortService) contex.getBean("orderShortService");
		OrderShortService orderShortService2 = (OrderShortService) contex.getBean("orderShortService");
		appUserService = (AppUserService) contex.getBean("appUserService");
	}

	@Test
	public void testOrder() {
		orderShortService.orderTest();
	}

	@Test
	public void testSwitch() {
		String a = "";
		switch (a) {
			case "a":
				System.out.println("AAAAAAAAAAA");
				break;
			default:
				System.out.println("默认啦啦啦啦");
				break;
		}

		System.out.println("啦啦啦啦啦啦啦");
	}

	@Test
	public void testa() {
		((OrderTest) AopContext.currentProxy()).testSwitch();
	}

	@Test
	public void testLi() {
		List<String> list = new ArrayList<>();
		list.add("ABC");

		List<String> temp = list.subList(1, 1);
		for (String a : temp) {
			System.out.println(a);
		}
	}

	@Test
	public void testBa() {
		appUserService.userBatsi();
	}

}
