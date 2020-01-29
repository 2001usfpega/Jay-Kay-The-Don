package com.revature.compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CompareName {

	public static void main(String[] args) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		studentList.addAll(Arrays.asList(new Student[] { new Student(15, "Matt", 6.54), new Student(34, "AJ", 10),
				new Student(21, "Torres", 1.45), new Student(12, "Amanda", 11.9), new Student(67, "Jay-$", 0.9),
				new Student(99, "Kaelin", 2.5), new Student(44, "Alia", 4.0) }));
		
		System.out.println("Original List: ");
		for (Student s : studentList) {
			System.out.println(s);
		}

		System.out.println("+++++++++++++++++++++++++++++++++");
		Collections.sort(studentList);
		for (Student s : studentList) {
			System.out.println(s);
		}
		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("Sorted by GPA: ");
		Collections.sort(studentList, new StudewntComparator());
		for(Student s : studentList) {
			System.out.println(s);
		}
		System.out.println("+++++++++++++++++++++++++++++++++++");
		System.out.println("Sorted by label w/ lamda: ");
		//Functional interfaces have 1 (not default) method
		//we can do lambda w/ these
		Collections.sort(studentList, (arg0, arg1);
			-> {
				return arg0.getLabel().compareTo(arg1.getLabel());
		for(Student s : studentList) {
			System.out.println(s);
		}
	}

}