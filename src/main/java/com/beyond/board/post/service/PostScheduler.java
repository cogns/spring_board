package com.beyond.board.post.service;

import com.beyond.board.post.domain.Post;
import com.beyond.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

//@Component
//public class PostScheduler {
//
//    private final PostRepository postRepository;
//    @Autowired
//    public PostScheduler(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
//
////    아래 스케쥴의 cron부는 각 자리마다 "초 분 시간 일  월 요일"을 의미
////    ex) * * * * * * : 매월 매요일 매일 매분 매초마다 시간
////    ex) 0 0 * * * * : 매일 매시 0분 0초에
////    ex) 0 0 11 * * * : 매일 11시에
////    ex) 0 0/1 * * * * : 매월 매시 1분마다 1시간에 60번 돌아감
////    ex) 0 1 * * * * : 매월 매시 1분에. 1시간에 1번 돌아감
//    @Scheduled(cron = "0 0/1 * * * *")
//    @Transactional
//    public void postSchedule(){
//        System.out.println("===예약 글쓰기 스케쥴러 시작===");
////        paging 안 하고 y인 대상으로 for문 돌리기
//        Page<Post> posts = postRepository.findByAppointment(Pageable.unpaged(),"Y");
//        for(Post p : posts){
//            // 예약 시간이 나우보다 이전이면
//            if(p.getAppointmentTime().isBefore(LocalDateTime.now())){
//                // N 처리
//                p.updateAppointment("N");
//            }
//        }
//    }
//
//}
