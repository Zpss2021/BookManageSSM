package info.zpss.controller;

import info.zpss.service.BookService;
import info.zpss.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BorrowController {
    @Resource
    private BookService bookService;

    @Resource
    private UserService userService;

    @GetMapping({"/borrow", "/"})
    public String borrow(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("borrow_list",bookService.getBorrowList());
        model.addAttribute("student_count", userService.getStudentList().size());
        model.addAttribute("book_count", bookService.getBookList().size());
        return "borrow";
    }

    @GetMapping("/add-borrow")
    public String addBorrow(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("nickname", user.getUsername());
        model.addAttribute("book_list", bookService.getActiveBookList());
        model.addAttribute("student_list", userService.getStudentList());
        return "add-borrow";
    }

    @PostMapping("/add-borrow")
    public String addBorrow(Integer student, Integer book) {
        bookService.addBorrow(student, book);
        return "redirect:/borrow";
    }

    @GetMapping("/return-book")
    public String returnBook(Integer id) {
        bookService.returnBook(id);
        return "redirect:/borrow";
    }
}
