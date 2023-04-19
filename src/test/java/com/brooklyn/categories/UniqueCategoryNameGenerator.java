package com.brooklyn.categories;

import java.util.HashSet;
import java.util.Set;

import com.github.javafaker.Faker;

public class UniqueCategoryNameGenerator {
    
    private Set<String> usedNames = new HashSet<>();
    private Faker faker = new Faker();
    
    public String generate() {
        String name;
        do {
            name = faker.book().genre();
        } while (usedNames.contains(name));
        usedNames.add(name);
        return name;
    }
    public static void main(String[] args) {
    	Faker faker = new Faker();
    	Set<String> categories = new HashSet<>();

    	while (categories.size() < 50) {
    	    String category = faker.lorem().word();
    	    if (categories.add(category)) {
    	        System.out.println(category);
    	    }
    	}
	}
}