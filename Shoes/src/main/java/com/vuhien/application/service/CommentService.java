package com.vuhien.application.service;

import com.vuhien.application.entity.Comment;
import com.vuhien.application.model.request.CreateCommentPostRequest;
import com.vuhien.application.model.request.CreateCommentProductRequest;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Comment createCommentPost(CreateCommentPostRequest createCommentPostRequest,long userId);
    Comment createCommentProduct(CreateCommentProductRequest createCommentProductRequest, long userId);
}
