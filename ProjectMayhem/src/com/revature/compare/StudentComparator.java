package com.revature.compare;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return (int) ((o1.getGpa()-o2.getGpa())*100);
	}
	
	
	
	//2nd method of comparing 

}
