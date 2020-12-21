package com.sunil.springeducation.service;

import com.sunil.springeducation.datamodel.SaleGroupByUserId;
import com.sunil.springeducation.datamodel.UserGradeEnum;
import com.sunil.springeducation.datamodel.UserTotalPaidPrice;
import com.sunil.springeducation.model.User;
import com.sunil.springeducation.repository.SaleRepository;
import com.sunil.springeducation.repository.UserRepository;
import com.sunil.springeducation.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserService {
    private final UserRepository userRepository;
    private final SaleRepository saleRepository;

    @Autowired
    public UserService(UserRepository userRepository, SaleRepository saleRepository) {
        this.userRepository = userRepository;
        this.saleRepository = saleRepository;
    };

    public User find(int userId) throws Exception{
        Optional<User> searchedUser = this.userRepository.findById(userId);

        return searchedUser.orElseThrow(() -> new Exception("해당 유저를 찾지 못하였습니다"));
    };

    public List<User> findAll() {
        return this.userRepository.findAll();
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

    public int createUser(UserRegisterVO user) {
        User createUser = User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .build();

        this.userRepository.save(createUser);
        this.userRepository.flush();

        return createUser.getUserId();
    };

    public void deleteUser(int userId) {
        this.userRepository.deleteById(userId);
    };

    public UserGradeEnum getUserGrade(int userId) {
        SaleGroupByUserId groupData = this.saleRepository.PurchaseAmountGroupByUserId(userId);
        UserTotalPaidPrice userTotalPaidPrice = new UserTotalPaidPrice(groupData);

        return this.getUserGradeByTotalPaidPrice(userTotalPaidPrice.getTotalPaidPrice());
    };

    public UserGradeEnum getUserGradeByTotalPaidPrice(int totalPaidPrice) {
        if(totalPaidPrice < 100000) {
            return UserGradeEnum.FirstGrade;
        }else if(totalPaidPrice < 1000000) {
            return UserGradeEnum.SecondGrade;
        }else if(totalPaidPrice < 3000000) {
            return UserGradeEnum.ThirdGrade;
        }else if(totalPaidPrice < 10000000) {
            return UserGradeEnum.FourthGrade;
        }else return UserGradeEnum.TopTier;
    };
}
