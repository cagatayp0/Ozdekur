package test;

import entities.Professor;

public class CheckIdTest {
	
	public static void main(String[] args) {
		
		Professor p = new Professor();
		p.setId("12356745600");
		System.out.println(p.checkId(p.getId()));
		
	}
	
}
