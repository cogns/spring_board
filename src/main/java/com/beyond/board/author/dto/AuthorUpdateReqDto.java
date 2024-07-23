package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateReqDto {
    private String name;
    private String password;

    public Author updateEntity(){
        Author author = Author.builder()
                .name(this.name)
                .password(this.password)
                .build();
        return author;
    }
}
