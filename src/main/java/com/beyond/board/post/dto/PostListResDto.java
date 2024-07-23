package com.beyond.board.post.dto;

import com.beyond.board.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostListResDto {
    private Long id;
    private String title;
//    Author객체 그 자체를 return하개 되면 Author안에 Post가 재참조되어 순환참조 이슈 발생.
    private String author_email;
//    private Author author; //순환참조 예시
}
