package com.beyond.board.author.domain;

import com.beyond.board.author.dto.AuthorDetailResDto;
import com.beyond.board.author.dto.AuthorListRestDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
//Builder 어노테이션을 통해 매개변수의 순서, 매개변수의 개수 등을 유연하게 세팅하여 생성자로서 활용
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

//    일반적으로 부모 Entity(자식 객체에 영향을 끼칠 수 있는 엔티티)의 cascade 옵션을 설정함
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts; //여기에는 무조건 쓰진 않아도 됌.


    public AuthorListRestDto fromEntinty(){
        AuthorListRestDto authorListRestDto = AuthorListRestDto.builder()
                .id(this.id).name(this.name).email(this.email).build();
        return authorListRestDto;
    }

//    public AuthorDetailResDto fromDetailEntinty(){
//        return AuthorDetailResDto.builder()
//                .id(this.id)
//                .name(this.name)
//                .email(this.email)
//                .password(this.password)
//                .role(this.role)
//                .createdTime(this.createdTime)
//                .build();
//    }

    public void updateAuthor(AuthorUpdateReqDto dto){
        this.name = dto.getName();
        this.password = dto.getPassword();
    }
}
