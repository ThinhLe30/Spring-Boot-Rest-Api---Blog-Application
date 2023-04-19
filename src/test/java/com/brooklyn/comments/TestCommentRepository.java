package com.brooklyn.comments;

import java.util.Random;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.brooklyn.entity.Comment;
import com.brooklyn.repository.CommentRepository;
import com.brooklyn.repository.PostRepository;
import com.github.javafaker.Faker;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestCommentRepository {
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private PostRepository postRepository;
	@Test
	public void testFakeDataCommment() {
		Faker faker = new Faker();
		Random random = new Random();
		IntStream.range(0, 50).forEach(i -> {
			Comment comment = new Comment();
			comment.setName(faker.name().fullName());
			comment.setEmail(faker.internet().emailAddress());
			comment.setBody(faker.lorem().sentence(10));
			comment.setPost(postRepository.findById(random.nextInt(50) + 31).get());
			commentRepository.save(comment);
		});
	}
}
