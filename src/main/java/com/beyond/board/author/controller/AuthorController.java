package com.beyond.board.author.controller;


import com.beyond.board.author.dto.AuthorDetailResDto;
import com.beyond.board.author.dto.AuthorListRestDto;
import com.beyond.board.author.dto.AuthorSaveReqDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class AuthorController {
    private final AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

//    @PostMapping("/author/create")
//    private String authorCreate(@RequestBody AuthorSaveReqDto dto){
//        authorService.authorCreate(dto);
//        return "ok";
//    }

    @GetMapping("/author/register")
    public String authorCreateScreen(){
        return "Author/author_register";
    }

    @PostMapping("/author/register")
    public String authorCreate(@ModelAttribute AuthorSaveReqDto dto){
        authorService.authorCreate(dto);
        return "redirect:/author/list";
    }


//    @GetMapping("/author/list")
//    public List<AuthorListRestDto> authorList(){
//        return authorService.authorList();
//    }

    @GetMapping("/author/list")
    public String authorList(Model model){
        List<AuthorListRestDto> authorListRestDtos = authorService.authorList();
        model.addAttribute("authorList", authorListRestDtos);
        return "Author/author_list";
    }


//    @GetMapping("/author/detail/{id}")
//    public AuthorDetailResDto authorDetail(@PathVariable Long id){
//        return authorService.authorDetail(id);
//    }


    @GetMapping("/author/detail/{id}")
    public String authorDetail(@PathVariable Long id, Model model){
//        log.info("get요청이고, parameter는" + id);
//        log.info("method명 : authorDetail");
        model.addAttribute("author", authorService.authorDetail(id));
        return "Author/author_detail";
    }


    @GetMapping("/author/delete/{id}")
    public String authorDelete(@PathVariable Long id){
        authorService.delete(id);
        return "redirect:/author/list";
    }


    @PostMapping("/author/update/{id}")
    public String authorUpdate(@PathVariable Long id, @ModelAttribute AuthorUpdateReqDto authorUpdateReqDto,  Model model){
        authorService.update(id, authorUpdateReqDto);
        return "redirect:/author/detail/"+id;
    }
}
