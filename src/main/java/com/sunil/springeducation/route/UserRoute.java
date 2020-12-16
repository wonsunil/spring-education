package com.sunil.springeducation.route;

import com.sunil.springeducation.model.User;
import com.sunil.springeducation.service.UserService;
import com.sunil.springeducation.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRoute {

    private final UserService userService;

    @Autowired
    public UserRoute(UserService userService) {
        this.userService = userService;
    };

    @GetMapping("")
    @ResponseBody
    public List<User> getUsers() {
        return this.userService.findAll();
    };

    @GetMapping("/{userId}")
    @ResponseBody
    public User getUser(@PathVariable(value = "userId") String userId) throws Exception{
        return this.userService.find(Integer.parseInt(userId));
    };

    @PostMapping("")
    public int createUser(UserRegisterVO user) {
        return this.userService.createUser(user);
    };

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable(value = "userId") String userId) {
        this.userService.deleteUser(Integer.parseInt(userId));
    };

    @GetMapping("/initialize")
    public void initializers() {
        this.userService.initializeUsers();
    };
}
