package lab_20;
import java.util.*;

public class TestGenerics1 {
	public static void main(String[] args) {
		new TestGenerics1().go();
	}
	public void go() {
		Dog[] dogs = {new Dog(),new Dog(),new Dog()};
		//ArrayList<Dog> dogs = new ArrayList<Dog>(); dogs.add(new Dog()); dogs.add(new Dog()); dogs.add(new Dog());
		takeAnimals(dogs);
	}
	public void takeAnimals(ArrayList<? extends Animal> animals) {
		for(Animal a : animals) {
			a.eat();
			animals.add(new Cat());
		}
	}
	public void takeAnimals(Animal[] animals) {
		for(Animal a : animals) {
			a.eat();
			animals[0] = new Cat();
		}
	}
	abstract class Animal{
		void eat() {
			System.out.println("eating is processing");
		}
	}
	class Dog extends Animal{
		void bark() {}
	}
	class Cat extends Animal{
		void meow() {}
	}
}
