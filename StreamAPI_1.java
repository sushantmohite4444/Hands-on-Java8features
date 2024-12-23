package deepPractice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI_1 {

	public static void main(String[] args) {

		Integer[] numbs = { 1, 3, 4, 7, 2, 9, 3, 37, 55, 77, 22, 88, 44 };

		List<Integer> stream = Arrays.stream(numbs).filter(x -> (x % 2 == 0)).map(x -> x + 1).distinct().limit(5)
				.skip(1).sorted((x, y) -> y - x).collect(Collectors.toList());

		System.out.println(stream);
		
		
		
	   Integer max=  Stream.iterate(1, x-> x+1).limit(50).filter(x-> x%5==0).max((x,y)->x-y).get();
	    
	    System.out.println(max);

	}

}

class employee1 {

	private int id;
	private int age;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "employee1 [id=" + id + ", age=" + age + ", name=" + name + "]";
	}
}
