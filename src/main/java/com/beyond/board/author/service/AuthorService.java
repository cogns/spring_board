package com.beyond.board.author.service;


import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorDetailResDto;
import com.beyond.board.author.dto.AuthorListRestDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
//조회작업시 readOnly 설정하면 성능향상. 다만, 저장 작업시에는 Transactional필요
public class AuthorService {
    private final AuthorRepository authorRepository;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author authorCreate(AuthorSaveReqDto dto){
        if (authorRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 email입니다.");
        }
        if (dto.getPassword().length()<4){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }
        Author author = dto.toEntity();
//        casecade persist 테스트. remove테스트는 회원삭제로 대체
        author.getPosts().add(Post.builder().title("가입인사").author(author).contents("안녕하세요"+dto.getName()+"입니다.") .build());
        Author savedAuthor = authorRepository.save(author);
        return savedAuthor;
    }


    public List<AuthorListRestDto> authorList(){
        List<Author> authors = authorRepository.findAll();
        List<AuthorListRestDto> authorListResDtoList = new ArrayList<>();
        for (Author a : authors){
            AuthorListRestDto authorListRestDto = a.fromEntinty();
            authorListResDtoList.add(authorListRestDto);
        }
        return authorListResDtoList;
    }

//    public AuthorDetailResDto authorDetail(Long id){
//        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("member is not found"));
//        AuthorDetailResDto authorDetailDto = author.fromDetailEntinty();
//        return authorDetailDto;
//    }

    public AuthorDetailResDto authorDetail(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("member is not found"));
        AuthorDetailResDto authorDetailResDto = new AuthorDetailResDto();
        return authorDetailResDto.fromEntity(author);
    }


    public Author authorFindByEmail(String email){
        Author author = authorRepository.findByEmail(email).orElseThrow(()-> new EntityNotFoundException("해당 email의 사용자는 없습니다."));
        return author;
    }

    @Transactional
    public void delete(Long id){
        authorRepository.deleteById(id);
    }


    @Transactional
    public void update(Long id, AuthorUpdateReqDto dto){
    Author author = authorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("member is not found"));
    author.updateAuthor(dto);
//    jpa가 특정 엔티티의 변경을 자동으로 인지하고 변경사항을 DB에 반영하는것이 dirtychecking(변경감지)
//    dirty checking시 @Transactional 어노테이션 반드시 필요함
//    authorRepository.save(author);
    }


}
