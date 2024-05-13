package com.example.java5_s2_controllers.controllers;

import com.example.java5_s2_controllers.entities.Student;
import com.example.java5_s2_controllers.services.StudentService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
//@RequestMapping(path = "/basic") // 4. on class level
public class BasicController {

    final
    StudentService studentService;

    public BasicController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/students")
    public String showStudents(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "home";
    }

/*
    // 1.
    // http://localhost:8080/hello
    // http://localhost:8080/hello?name=Poly@email=gmail
    @GetMapping(value ="/hello")
    @ResponseBody
    public String sayHelloV1() {
        return "Hello World!";
    }
*/

    // 2. @RequestParam
    // http://localhost:8080/hello?name=Poly@email=gmail
    // http://localhost:8080/hello?username=Poly&email=gmail

    //@GetMapping(value = "/hello")
    //@ResponseBody
    //public String sayHelloV2a(@RequestParam(name = "username") @Nullable String name) {
    //    return "Hello " + name + "!";
    //}

    //@GetMapping(value = "/hello")
    //@ResponseBody
    //public String sayHelloV2b (
    //        @RequestParam("username") @Nullable String name,
    //        @RequestParam(name = "email", required = false) String email
    //)
    //{
    //    return "Hello " + name + " -- " + email;
    //}


    // Optional<T>
    //http://localhost:8080/hello?username=Poly&email=Gmail
    //http://localhost:8080/hello?email=Gmail
    //@GetMapping(value = "/hello")
    //@ResponseBody
    //public String sayHelloV2c (
    //        @RequestParam("username") Optional<String> name,
    //        @RequestParam(name = "email", required = false) String email
    //)
    //{
    //    return "Hello " + name.orElseGet(() -> "Not provided") + " -- " + email;
    //}

    // defaultValue
    //http://localhost:8080/hello?username=Poly&email=Gmail
    //http://localhost:8080/hello?email=Gmail
    //@GetMapping(value = "/hello")
    //@ResponseBody
    //public String sayHelloV2d (
    //        @RequestParam("username") Optional<String> name,
    //        @RequestParam(name = "email", defaultValue = "default-EMAIL") String email
    //)
    //{
    //    return "Hello " + name.orElseGet(() -> "Not provided") + " -- " + email;
    //}

    // get all params
    //http://localhost:8080/hello?username=Poly&email=Gmail
    //@GetMapping(value = "/hello")
    //@ResponseBody
    //public String sayHelloV2e (
    //        @RequestParam Map<String, String> allParams
    //        )
    //{
    //    return "Parameters are: " + allParams.entrySet();
    //}

    // with params
    //http://localhost:8080/hello?username=Poly&email=Gmail
    @GetMapping(value = "/hello", params = {"username", "email"})
    @ResponseBody
    public String sayHelloV2f (
            @RequestParam Map<String, String> allParams
            )
    {
        return "Parameters are: " + allParams.entrySet();
    }



    // 3. @PathVariable
    // http://localhost:8080/hello/1
    @GetMapping(value = "/hello/{id}")
    @ResponseBody
    public String sayHelloV3a(@PathVariable("id") int id) {
        return "Hello student with id = " + id + "!";
    }

    // http://localhost:8080/hello/1
    @GetMapping(value = "/hello/{id}/{email}")
    @ResponseBody
    public String sayHelloV3b(
            @PathVariable("id") int id,
            @PathVariable("email") String myemail
    ) {
        return "Hello student with id = " + id + " email = " + myemail;
    }


    //4. @RequestMapping on Class level


    //5. @RequestMapping with headers
    @RequestMapping(value="/method4", headers="key1=value1")
    @ResponseBody
    public String method4(){
        return "method4";
    }

    @RequestMapping(value="/method5", headers={"key1=value1", "id=1"})
    @ResponseBody
    public String method5(){
        return "method5";
    }

    //6.@RequestMapping with Produces and Consumes
    @RequestMapping(value="/method6", produces={"application/json","application/xml"}, consumes="text/html")
    @ResponseBody
    public String method6(){
        return "method6";
    }


}
