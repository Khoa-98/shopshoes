package com.vuhien.application;


import com.vuhien.application.entity.Post;
import com.vuhien.application.repository.PostRepository;
import com.vuhien.application.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;


import java.util.List;

@SpringBootTest
public class PostTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Test
    void findAllPostByStatus_Test(){

        Page<Post> posts = postService.getListPost(1);
        posts.forEach(System.out::println);
    }

    @Test
    void updatePost_test(){

    }
}
