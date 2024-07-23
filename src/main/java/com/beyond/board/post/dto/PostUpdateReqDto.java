package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.post.domain.Post;
import lombok.Data;

@Data
public class PostUpdateReqDto {
    private String title;
    private String contents;

    public Post updateEntity(){
        Post post = Post.builder()
                .title(this.title)
                .contents(this.contents)
                .build();
        return post;
    }
}
