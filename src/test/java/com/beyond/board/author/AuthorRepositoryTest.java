package com.beyond.board.author;


import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional //롤백처리를 위해 Transactional어노테이션 사용
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void authorSaveTest(){
//        테스트원리 : save -> 재조회 -> 저장할때 만든 객체와 비교
//        준비(prepare, given)
        Author author = Author.builder()
                .name("kwon2")
                .email("kwon2@naver.com")
                .password("12343")
                .role(Role.ADMIN)
                .build();
//        실행(execute, when)
        authorRepository.save(author);
        Author savedAuthor = authorRepository.findByEmail("kwon2@naver.com").orElse(null);

//        검증(then)
        Assertions.assertEquals(author.getEmail(), savedAuthor.getEmail());

    }

}
