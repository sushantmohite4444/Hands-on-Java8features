package deepPractice;

@FunctionalInterface
interface One {
	void get(int a);
}

@FunctionalInterface
interface Two {
	int get();
}

public class LambdaExpression {

	public static void main(String[] args) {
		int a=190;
		
		 One one = (x) -> System.out.println("Implementation class is not require " );
		 one.get(10);
		 
		 Two two = new Two() {
			@Override
			public int get() {
				return a;
			}
		};
		 System.out.println(two.get());  
		
		
		
		
	}
}
