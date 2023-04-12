package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";     // controller에서 문자값으로 return하면 'viewResolver'가 해당 화면을 찾아 처리함
                            // SpringBoot 템플릿 엔진은 기본적으로 viewName mapping
                            // => resources/templates/ + {ViewName(hello)}.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식 : 객체(json 형식)로 반환
    @GetMapping("hello-string")
    @ResponseBody   // Body에 return 데이터를 직접 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;     // "hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody       // json 형식으로 반환 {key:value}
                        // viewResolver 대신 'HttpMessageConverter' 동작
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}