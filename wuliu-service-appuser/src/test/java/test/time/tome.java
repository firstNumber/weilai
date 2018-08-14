package test.time;

import java.text.MessageFormat;

public class tome {
	public static void main(String[] args) {
		// String aa = "15910995356";
		// System.out.println(aa.substring(aa.length() - 6, aa.length()));
		//
		// String pwd = MD5Util.encode("032966" + "7031");
		// System.out.println(pwd);
		// testMessageFormat();

		// String a = "";
		// System.out.println(a.length());
		//
		// Date date = new Date();
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(date);
		// calendar.add(calendar.DATE, -1);
		// // calendar.set(Calendar.HOUR_OF_DAY, hour);
		// // calendar.set(Calendar.MINUTE, minute);
		// // calendar.set(Calendar.SECOND, second);
		// Date maxDate = calendar.getTime();
		// System.out.println(maxDate);
		// SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm:ss");
		// System.out.print(dateFormater.format(maxDate));
		// Map map = new HashMap();
		// System.out.println();
		// testHierarchical();

	}

	public static void testHierarchical() {
		System.out.println(tome.class);

	}

	public static void testMessageFormat() {
		String a = MessageFormat.format("213", "里", "哈哈");
		System.out.println(a);
	}
}
