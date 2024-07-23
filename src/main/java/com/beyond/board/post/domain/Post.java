package com.beyond.board.post.domain;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.dto.PostDetailResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostUpdateReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 3000)
    private String contents;

    private String appointment;
    private LocalDateTime appointmentTime;

//    연관관계의 주인은 fk가 있는 post
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;


    public PostListResDto listFromEntinty() {
        return PostListResDto.builder()
                .id(this.id)
                .title(this.title)
//                .author(this.author) //순환참조 예시
                .author_email(this.author.getEmail())
                .build();
    }


    public PostDetailResDto detailFromEntity(){
        return PostDetailResDto.builder()
                .id(this.id)
                .title(this.title)
                .author_email(this.author.getEmail())
                .contents(this.contents)
                .createdTime(this.getCreatedTime())
                .updateTime(this.getUpdateTime())
                .build();
    }


    public void updateAppointment(String yn){
        this.appointment = yn;
    }


    public void updatePost(PostUpdateReqDto dto){
        this.title = dto.getTitle();
        this.contents = dto.getContents();
    }
}
