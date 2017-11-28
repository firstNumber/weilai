package sta;

public class StaClass {
	private String name;

	public static void main(String[] args) {
		 a b = new a();
		 b.a();
		 
	}
}

class a {
	public void a(){
		Thread  t = new Thread(new Runnable() {
			public void run() {
				System.out.println("1");
			}
		});
	}

}
