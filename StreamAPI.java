package deepPractice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPI {

	public static void main(String[] args) {

		List<Integer> numb = Arrays.asList(12, 212, 23, 123, 422, 32);

		Stream<Integer> strm = numb.stream();

		Stream<Integer> f = strm.filter(n -> n % 2 == 0);

		Stream<Integer> m = f.map(n -> n * 2);

		int j = m.reduce(0, (i, k) -> i + k);

		int o = numb.stream().filter(n -> n % 2 == 0).map(n -> n * 2).reduce(0, (c, e) -> c + e); // c is carry here 0+1
																									// =1

		System.out.println(o);

//	   m.forEach(n->System.out.println(n));

		// parallelstream

		int size = 100;
		int i = 0;
		List<Integer> numbs = new ArrayList<Integer>();

		Random rm = new Random();

		while (i <= size) {
//			numbs.add(rm.nextInt(100));
			numbs.add(i);
			i++;
		}

		long normalstrm = System.currentTimeMillis();

//	  int sum = numbs.stream().map((n)->{ try {
//		  Thread.sleep(1);} catch (InterruptedException e1) {} return n*2;}).reduce(0,(c,e)->c+e);

		int sum = numbs.stream().map((n) -> {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
			}
			return n * 2;
		}).mapToInt(e -> e).sum();

		long normalstrmend = System.currentTimeMillis();

		long parallelstrm = System.currentTimeMillis();

		int sum1 = numbs.parallelStream().map((n) -> {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
			}
			return n * 2;
		}).mapToInt(e -> e).sum();

		long parallelstrmend = System.currentTimeMillis();

		System.out.println(sum + " normal time= " + (normalstrmend - normalstrm) + "    " + sum1 + " parallel time = "
				+ (parallelstrmend - parallelstrm));

		Function<Integer, Integer> mapper = new Function<Integer, Integer>() {

			@Override
			public Integer apply(Integer t) {

				return t * 2;
			}
		};

		BinaryOperator<Integer> reduce = new BinaryOperator<Integer>() {

			@Override
			public Integer apply(Integer t, Integer u) {

				return t + u;
			}
		};

//		numbs.stream().map(mapper).forEach(n-> System.out.println(n));
		System.out.println(numbs.stream().reduce(reduce));
		System.out.println(numbs.stream().reduce((c, t) -> c + t));

		String Names[] = { "Sush", "Sushant", "sm" };

		Stream<String> names = Stream.of(Names);

		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {

				return ((o1.length() < o2.length()) ? 1 : -1);
			}
		};

		System.out.println(names.max(comparator));

		employee e1 = new employee(1, 20, 100);
		employee e2 = new employee(2, 100, 120);
		employee e3 = new employee(3, 20, 100);
		employee e4 = new employee(4, 150, 120);
		employee e5 = new employee(5, 120, 90);
		employee e6 = new employee(6, 130, 120);
		employee e7 = new employee(7, 160, 90);

		List<employee> Employee = Arrays.asList(e1, e4, e2, e3, e7, e5, e6);

		Comparator<employee> comparator2 = new Comparator<employee>() {

			@Override
			public int compare(employee o1, employee o2) {

				return ((o1.age < o2.age) ? 1 : -1);
			}
		};

		System.out.println("print employee list in decending order");
		Employee.stream().sorted((o1, o2) -> (o1.age < o2.age) ? 1 : -1).forEach(e -> System.out.println(e.toString()));

		System.out.println();
		// internally call list as a second parameter
		Map<Integer, List<employee>> groupbuy = Employee.stream().collect(Collectors.groupingBy(employee::getName));

//		       System.out.println("Group by Name");
//		    Iterator<Map.Entry<Integer, List<employee>>> empIterator = groupbuy.entrySet().iterator();
//		    
//		    while (empIterator.hasNext()) {
//		    	Map.Entry<Integer, List<employee>> entry=     empIterator.next();
//		    	System.out.println(entry.getKey() + ": " + entry.getValue());
//			}
//		       groupbuy.forEach((a,b)->System.out.println(a + "  " + b));
//		       

		groupbuy.entrySet().stream().forEach((e) -> System.out.println(e.getKey() + "" + e.getValue()));

		System.out.println();
		// internally call Hashmap as a second argument
		Map<Integer, Integer> GroupAndAverage = Employee.stream()
				.collect(Collectors.groupingBy(employee::getName, Collectors.summingInt(employee::getAge)));

		System.out.println("Group by name and summing of age");
		GroupAndAverage.forEach((p, k) -> System.out.println("GroupName :" + p + "  AgeTotal  :" + k));
		GroupAndAverage.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));

		System.out.println();

		System.out.println("Group by name Unique age (Hahcode () and equals()) Internaly uses HashMap() unsorted");
		Map<Integer, Set<employee>> set = Employee.stream()
				.collect(Collectors.groupingBy(employee::getName, Collectors.toSet()));

		set.forEach((key, value) -> System.out.println(key + " HashMap  " + value));

		System.out.println();

		System.out.println(
				"Group by name Unique age (Hahcode () and equals()) Explicitly mention TreeMap for Sorted map");

		Map<Integer, Set<employee>> setwithOrder = Employee.stream()
				.collect(Collectors.groupingBy(employee::getName, TreeMap::new, Collectors.toSet()));

		setwithOrder.forEach((key, value) -> System.out.println(key + " TreeMap " + value));

		System.out.println();
		
		System.out.println("Partitioning Method");

		Map<Boolean, List<employee>> op = Employee.stream().collect(Collectors.partitioningBy(x -> x.getAge() > 100));
		op.forEach((s, l) -> System.out.println(s + "" + l));
		
		System.out.println();
//		peek(System.out::println)
		
		System.out.println("Max method is different in Stream and IntStream");
		
		  OptionalInt  z = Employee.stream().mapToInt(employee::getAge).max();
		  System.out.println(z);
		  
		  System.out.println();
		  
		  System.out.println("Summarizing int provide 5 opration at a time ");
		  
		  Map<Integer, IntSummaryStatistics> summaty  = Employee.stream()
					.collect(Collectors.groupingBy(employee::getName, Collectors.summarizingInt(employee::getAge)));

		  
		  System.out.println(summaty);
		  System.out.println();
		 

		

		System.out.println(Employee.stream().filter((x) -> (x.age > 150)).map(p -> p).collect(Collectors.toList()));

		Predicate<String> startwith = (t) -> t.toLowerCase().startsWith("s");
		Predicate<String> endwith = (t) -> t.toUpperCase().endsWith("T");
		Predicate<String> combo = startwith.and(endwith);
		System.out.println(combo.negate().test("Sushant"));

		Function<Integer, Integer> Multiplication = l -> l * 2;
		// System.out.println(Multiplication.apply(8));
		Function<Integer, Integer> square = a -> a * a;
		// System.out.println(square.apply(10));

		Function<Integer, Integer> multiThenSquare = Multiplication.andThen(square);
//		Function<Integer, Integer> squareThenMulti = square.andThen(Multiplication);
		// System.out.println( multiAndSqure.apply(7));

		Function<Integer, Integer> identity = Function.identity();

		System.out.println(identity.apply(4444));

		Consumer<String> consumer = x -> System.out.println(x);
		Supplier<String> supplier = () -> "Supply Something";

		consumer.accept(supplier.get());

//	   BiFunction<T, U, R>;
//	   BiPredicate<T, U>;
//	   BiConsumer<T, U>;

	}

}

class employee {

	int id;
	int age;
	int name;

	public employee(int id, int age, int name) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
	}

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

	public int getName() {
		return name;
	}

	public void setName(int name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "employee [id=" + id + ", age=" + age + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(age);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		employee other = (employee) obj;
		return age == other.age;
	}

}
