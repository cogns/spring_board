package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostSaveReqDto {
    private String title;
    private String contents;
//    추후 로그인 시능 이후에는 없어질 dto
    private String email;

    private String appoinment;
    private String appoinmentTime;

    public Post toEntity(Author author, LocalDateTime appointmentTime){
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .appointment(this.appoinment)
                .appointmentTime(appointmentTime)
                .author(author)
                .build();
    }
}
