package PopulationСensus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {
		List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
		List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
		Collection<Person> persons = new ArrayList<>();
		for (int i = 0; i < 10_000_000; i++) {
		    persons.add(new Person(
		                names.get(new Random().nextInt(names.size())),
		                families.get(new Random().nextInt(families.size())),
		                new Random().nextInt(100),
		                Sex.values()[new Random().nextInt(Sex.values().length)],
		                Education.values()[new Random().nextInt(Education.values().length)])
		    );
		}
		long minorsCount = persons.stream()
				.filter(person -> person.getAge() < 18)
				.count();
		System.out.println("Количество несовершеннолетних: " + minorsCount);
		
		List<String> conscripts = persons.stream()
				.filter(person -> person.getSex() == Sex.MAN)
				.filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
				.map(Person::getFamily)
				.collect(Collectors.toList());
		System.out.println("Количество призывников: " + conscripts.size());
		
		
		List<Person> workablePeople = persons.stream()
				.filter(person -> person.getEducation() == Education.HIGHER)
				.filter(person -> 
				(person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60) || 
				(person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 65))
				.sorted(Comparator.comparing(Person::getFamily))
				.collect(Collectors.toList());
		System.out.println("Количество трудоспособных людей с высшим образованием: " + workablePeople.size());
	}
	

}
