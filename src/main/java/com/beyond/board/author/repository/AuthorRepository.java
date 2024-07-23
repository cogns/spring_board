package com.beyond.board.author.repository;

import com.beyond.board.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
//    findBy컬럼명의 규칙으로 자동으로 where조건문을 사용한 쿼리 생성
//    그 외 : findByNameAndEmail, findByNameOrEmail
//    findByAgeBetween(int start, int end) : start살~end살
//    findByAgeLessThen(int age) : age살보다 작은
//    findByAgeGreaterThen(int age) : age살보다 큰
//    findByAgeIsNull, findByAgeNotNull : null인지, notnull인지
//    ex) List<Author> findByNameIsNull(); , List<Author> findAllOrderByEmail();

    Optional<Author> findByEmail(String email);

}
