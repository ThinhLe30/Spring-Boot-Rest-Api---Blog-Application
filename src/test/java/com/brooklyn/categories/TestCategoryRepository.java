package com.brooklyn.categories;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.brooklyn.entity.Category;
import com.brooklyn.entity.Post;
import com.brooklyn.repository.CategoryRepository;
import com.brooklyn.repository.PostRepository;
import com.github.javafaker.Faker;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestCategoryRepository {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void testAddCategory(){
		Faker faker = new Faker();
		for (Category category : categoryRepository.findAll()) {
			category.setName(faker.lorem().word());
			categoryRepository.save(category);			
		}
	}
	@Test
	public void testAddCategoryToAllPost() {
		Random random = new Random();
		List<Post> posts = postRepository.findAll();
		for(Post post : posts) {
			Category category = categoryRepository.findById(random.nextInt(50)+2).get();
			post.setCategory(category);
			postRepository.save(post);
		}
	}
	
}
