package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController { // 아마 객체생성해서 쓰는듯 " 컨트롤러가 사용할 클래스 정의 아마 "
    @GetMapping("main") // 주소창에 get 방식으로 main 받으면 아래 메서드를 실행한다
    public String main(Model model) { // 모델객체를 파라미터로 받아서 모델에다가 데이터를 넣어준다
        model.addAttribute("index","this is main page"); // model에다가 값 넣어주고 쓴다
        return "main"; // 여기서 return하는 값은 resources>templates>main.html
    }
}