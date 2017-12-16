
public final class Person {
	final Student s;
	public Person(Student s){
		this.s = s;
	}
	// LinkedHashMap
	public static void main(String[] args) {
		Student s1 = new Student("one");
		Student s2 = new Student("two");
		Person p = new Person(s1);
		s1.setName("two");
	}
}
