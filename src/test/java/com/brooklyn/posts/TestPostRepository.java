package com.brooklyn.posts;

import java.util.UUID;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.brooklyn.entity.Post;
import com.brooklyn.repository.PostRepository;
import com.github.javafaker.Faker;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestPostRepository {
	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void testFakeDataToDB() {
		Faker faker = new Faker();
		IntStream.range(0, 50).forEach(i -> {
			postRepository.save(new Post(faker.book().title() + " " + UUID.randomUUID().toString(),
					faker.lorem().sentence(),
					faker.lorem().paragraph()));
		});
	}
}
