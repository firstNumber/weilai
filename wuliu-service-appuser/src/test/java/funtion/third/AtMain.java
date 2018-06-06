package funtion.third;

import java.util.concurrent.atomic.AtomicLong;

public class AtMain {

	public static void main(String[] args) {
		AtomicLong ato = new AtomicLong(0);
		System.out.println(ato);

		System.out.println(ato.incrementAndGet());
	}

}
