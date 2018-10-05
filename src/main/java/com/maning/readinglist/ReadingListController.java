package com.maning.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {

    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository=readingListRepository;
    }


    @GetMapping(value="/{reader}")
    public String readersBooks(@PathVariable String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books", readingList);
        }
        return  "readingList";
    }

    @PostMapping("/{reader}")
    public String addToReadingList(@PathVariable String reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }

    /*
    @RequestMapping("/readingList")
    public String readingList(Model model){
        return "readingList.html";
    }
    */

}