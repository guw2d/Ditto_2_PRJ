package com.ditto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor//클래스내부의 필드중 private final 로 선언된 inteface 의 하위 객체를 자동 주입시키는 어노테이션임.
@RequestMapping("/")//localhost/ditto(context)/list(path)
public class MainController {
    @GetMapping(value = {"/index"})
    public String main(){
        return "app/index";
    }  
    
    @GetMapping({"/","/admin"})
	public String index() {//viewer 를 리턴하도록 string 리턴 메서드 작성..
		log.info("list root 요청됨");
		return "redirect:/admin/main";
	}
}
