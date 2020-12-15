package com.sunil.springeducation.service;

import com.sunil.springeducation.model.User;
import com.sunil.springeducation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    };

    public User find(int userId) throws Exception{
        Optional<User> searchedUser = this.userRepository.findById(userId);

        if(searchedUser == null) throw new Exception("해당 유저를 찾지 못하였습니다");

        return searchedUser.get();
    }

    public void findAll() {
        for(User user: this.userRepository.findAll()) {
            System.out.println(user);
        }
    };

    public void initializeUsers() {
        User user1 = User.builder()
                .email("example@sample.com")
                .name("Mr. Example")
                .phone("01000000000")
                .build();

        User user2 = User.builder()
                .email("example2@sample.com")
                .name("Mrs. sample Data")
                .phone("01000001234")
                .build();

        User user3 = User.builder()
                .email("example3@sample.com")
                .name("ms. Sample Data")
                .phone("01012341234")
                .build();

        this.userRepository.save(user1);
        this.userRepository.save(user2);
        this.userRepository.save(user3);
        this.userRepository.flush();
    };
}
