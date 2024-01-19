package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


// 요약 : 객체를 반환하는 API 방식과 (ResponseBody사용), html 을 템플릿엔진이 렌더링해서 전달해주는 방식  2가지가 있다
// 1. 정적컨텐츠 방식 (파일을 그대로 전달)
// 2. MVC 방식 - 템플릿 엔진 방식 (html 을 템플릿 엔진이 프로그래밍해서 클라이언트에 전달)
// 3. MVC 방식 - ResponseBody 방식 (API 방식)

@Controller // MVC 모델을 이용 - Model 에 필요한 데이터를 담아서 화면에 전달해준다
public class HelloController {

    @GetMapping("hello") // localhost:8080/hello <- 입력시 attribute를 매핑해준다
//    getmapping 은 html 에서 get, post의 get 이다 (url 즉 헤더에 담겨온 데이터 이다 = hello 넣으면)
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; // attirbuteValue를 리턴하는게 아님
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // command + p 누르면 @requestparam 에 세팅된 정보 확인 가능, 기본값이 required = "true" 임 그래서 안넣으면 에러남
        // hello-mvc를 get으로 요청 시 매핑해주는 메서드이다
        // Request parameter 어노테이션을 통해 name을 입력받는다 , model에 매핑해주기 위해 파람으로 동시에 받는다
        model.addAttribute("name", name); // annotation으로 가져온 name을 model.name(키값) 에 value로 넣음
        return "hello-template";
    }


    //
//    @GetMapping("hello-mvc")
//    // RequestParam 으로 외부에서 직접 파라미터를 전달받는다.
//    // 외부에서 받은 name을 Model 에 넘겨준다
//    public String helloMvc(@RequestParam("name") String name, Model model) {
//        model.addAttribute("name", name); // 외부에서 받은 name을 모델에 넘겨준다
//        return "hello-template";
//    }
//
    // 예제 : 실제 쓰이는 방식은 아님
    // 안쓰이는 이유: ResponseBody를 써버리면 <html> <head> <body>이런거를 통틀어서 아예 바디로 바꿔서 리턴해줌 -> 아님!!
    // 따라서 진짜 쓰려면 "<html> <head> <meta name="author" ...> </head> <body></body></html> " 뭐 이렇게 해야함
    @GetMapping("hello-string")
    @ResponseBody // HTTP의 body부분에 직접 데이터를 추가해주는 어노테이션이다 -> viewResolver 동작 x HttpMessageConverter동작 o
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // 파라미터로 받은 값이 그대로 전달된다 : 이거 쓸일은 거의 없다 사실
    }

// 진짜 중요한 부분은 아래부분과 같다

//     요약 -> 객체는 JSON 으로 반환된다 {name : "hello!"}
//     @ResponseBody 이용하면 viewResolver 동작 X
//     기본 문자처리 : StringHttpMessageConverter
//     기본 객체처리 : MappingJackson2HttpMessageconverter
//     byte 처리 등등 : HttpMessageConverter가 기본으로 등록되어 있음
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 문자가 아닌 객체를 return 한다 -> JSON으로 뿌려준다
        // 만약 setter를 통해 'test'를 등록한 경우 { "name" : "test" }; JSON이 반환된다
    }
    static class Hello { // class 안에 class 사용? -> 자바 정식 지원 문법임
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}