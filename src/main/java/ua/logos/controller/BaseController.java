package ua.logos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BaseController {

    //@RequestMapping(value = "/",method = RequestMethod.GET)
    @GetMapping("/")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/news")
    public String showNews() {
        return "NEWS";
    }

    @GetMapping("/profile")
    public ResponseEntity<String> showProfile() {
        String myProfile = "It is my profile";
        return new ResponseEntity<String>(myProfile, HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<String>> getBooks(){
        List<String> books = new ArrayList<>();
        books.add("Book 1");
        books.add("Book 2");
        books.add("Book 3");
        books.add("Book 4");
        books.add("Book 5");
        books.add("Book 6");
        return new ResponseEntity<List<String>>(books,HttpStatus.OK);
    }


    @GetMapping("/books/{bookIndex}")
    public ResponseEntity<String> getBook(@PathVariable("bookIndex")Integer index){
        List<String> books = new ArrayList<>();
        books.add("Book 1");
        books.add("Book 2");
        books.add("Book 3");
        books.add("Book 4");
        books.add("Book 5");
        books.add("Book 6");
        String book = "Does not exist";
        if (index<books.size()){
            book=books.get(index);
        }
        return new ResponseEntity<String>(book,HttpStatus.OK);
    }
}
