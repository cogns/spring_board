package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

//    저장 및 detail조회
    @Test
    void saveAndFind() {
        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("hongildong", "hong@gmail.com", "12345", Role.ADMIN);
        Author author = authorService.authorCreate(authorDto);
        Author authorDetail = authorService.authorFindByEmail("hong@gmail.com");
        Assertions.assertEquals(authorDetail.getEmail(), authorDto.getEmail());
    }


//    update 검증
    @Test
    void updateTest(){
//        객체 create
        AuthorSaveReqDto authorDto = new AuthorSaveReqDto("hongildong", "hong@gmail.com", "12345", Role.ADMIN);
        Author author = authorService.authorCreate(authorDto);

//        수정작업(name, password)
        authorService.update(author.getId(), new AuthorUpdateReqDto("hong", "121212"));

//        수정후 재조회 : name, password가 맞는지 각각 검증
        Author savedAuthor = authorRepository.findById(author.getId()).orElseThrow(()->new EntityNotFoundException("없는 유저입니다."));
        Assertions.assertEquals("hong", savedAuthor.getName());
        Assertions.assertEquals("121212", savedAuthor.getPassword());
    }

//    findAll 검증
    @Test
    public void findAllTest(){
        int size = authorService.authorList().size();

//        3개 author객체 저장
        AuthorSaveReqDto authorDto1 = new AuthorSaveReqDto("hongildong", "hong1@gmail.com", "12345", Role.ADMIN);
        authorService.authorCreate(authorDto1);

        AuthorSaveReqDto authorDto2 = new AuthorSaveReqDto("hongildong", "hong2@gmail.com", "12345", Role.ADMIN);
        authorService.authorCreate(authorDto2);

        AuthorSaveReqDto authorDto3 = new AuthorSaveReqDto("hongildong", "hong3@gmail.com", "12345", Role.ADMIN);
        authorService.authorCreate(authorDto3);


//        authorList를 조회한 후 저장한 개수와 저장된 개수 비교
        Assertions.assertEquals(size+3, authorService.authorList().size());
    }

}
