package com.beyond.board.author.dto;


import com.beyond.board.author.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorListRestDto {
    private Long id;
    private String name;
    private String email;
}
