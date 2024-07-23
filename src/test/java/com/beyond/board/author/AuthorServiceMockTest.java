package com.beyond.board.author;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.domain.Role;
import com.beyond.board.author.dto.AuthorDetailResDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.author.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@SpringBootTest
@Transactional
public class AuthorServiceMockTest {

    @Autowired
    private AuthorService authorService;

//    @Autowired
//    private AuthorRepository authorRepository;

//    가짜 객체를 만드는 작업을 목킹이라 한다.
    @MockBean
    private AuthorRepository authorRepository;

    @Test
    public void findAuthorDetailTest(){
        AuthorSaveReqDto author = new AuthorSaveReqDto("test1", "test@naver.com", "12341234", Role.ADMIN);
        Author author1 = authorService.authorCreate(author);
//        Author mockAuthor = Author.builder().id(1L).name("test").email("test@naver.com").password("12232").build();
        AuthorDetailResDto authorDetailResDto = authorService.authorDetail(author1.getId());
//        Author savedAuthor = authorRepository.findById(author1.getId()).orElseThrow(()-> new EntityNotFoundException("없는 id입니다."));
        Mockito.when(authorRepository.findById(author1.getId())).thenReturn(Optional.of(author1));
        Author savedAuthor = authorRepository.findById(author1.getId()).orElseThrow(()-> new EntityNotFoundException("not found"));
        Assertions.assertEquals(authorDetailResDto.getEmail(), savedAuthor.getEmail());
    }

}
