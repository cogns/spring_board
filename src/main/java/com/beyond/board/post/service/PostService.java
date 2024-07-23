package com.beyond.board.post.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.service.AuthorService;
import com.beyond.board.post.domain.Post;
import com.beyond.board.post.dto.PostDetailResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostSaveReqDto;
import com.beyond.board.post.dto.PostUpdateReqDto;
import com.beyond.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final AuthorService authorService;

//    service 또는 repository를 autowired할지는 상황에 따라 다르나,
//    service레벨에서 코드가 고도화돼있고 코드의 중복이 심할경우, service레이어를 autowired
//    그러나, 순환참조가 발생할 경우에는 repository를 autowired
    @Autowired
    public PostService(PostRepository postRepository, AuthorService authorService){
        this.postRepository = postRepository;
        this.authorService = authorService;
    }


//    authorservice에서 author객체를 찾아 post의 toEntity에 넘기고,
//    jpa가 author객체에서 author_id를 찾아 db에는 author_id가 들어감.
    public Post postCreate(PostSaveReqDto dto){
        Author author = authorService.authorFindByEmail(dto.getEmail());
        String appointment = null;
        LocalDateTime appointmentTime = null;
        if (dto.getAppoinment().equals("Y") && !dto.getAppoinmentTime().isEmpty()){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            appointmentTime = LocalDateTime.parse(dto.getAppoinmentTime(), dateTimeFormatter);
            LocalDateTime now = LocalDateTime.now();
            if (appointmentTime.isBefore(now)){
                throw new IllegalArgumentException("시간입력이 잘못되었습니다.");
            }
        }
        System.out.println(dto);

        Post post = postRepository.save(dto.toEntity(author, appointmentTime));
        return post;
    }


    public Page<PostListResDto> PostList(Pageable pageable){
//        List<Post> posts = postRepository.findAllLeftJoin();
//        List<PostListResDto> postListResDtoList = new ArrayList<>();
//        for (Post p : posts){
//            postListResDtoList.add(p.listFromEntinty());
//        }
//        return postListResDtoList;

//        Page<Post> posts = postRepository.findAll(pageable);
        Page<Post> posts = postRepository.findByAppointment(pageable, "N");
        Page<PostListResDto> postListResDtos = posts.map(a->a.listFromEntinty());
        return postListResDtos;
    }


    public Page<PostListResDto> postListPage(Pageable pageable){
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostListResDto> postListResDtos = posts.map(a->a.listFromEntinty());
        return postListResDtos;
    }


    public PostDetailResDto postDetail(Long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("member is not found"));
        return post.detailFromEntity();
    }

    @Transactional
    public void delete(Long id){
        postRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, PostUpdateReqDto dto){
        Post post = postRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("post is not found"));
        post.updatePost(dto);
        postRepository.save(post);
    }
}
