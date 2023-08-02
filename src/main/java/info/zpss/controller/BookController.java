package info.zpss.controller;

import info.zpss.service.BookService;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/books")
    public String books(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("books", bookService.getBookMap());
        return "books";
    }

    @GetMapping("/add-book")
    public String addBook(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(String title, String desc, double price) {
        bookService.addBook(title, desc, price);
        return "redirect:/books";
    }

    @GetMapping("/delete-book")
    public String deleteBook(int id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }
}
