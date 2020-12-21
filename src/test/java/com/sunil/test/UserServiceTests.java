package com.sunil.test;

import com.sunil.springeducation.datamodel.enumModel.UserGradeEnum;
import com.sunil.springeducation.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    private final UserService userService;

    @Autowired
    public UserServiceTests(UserService userService) {
        this.userService = userService;
    };

    @Test
    public void testGetUserGradeByTotalPaidPriceFirstGrade() {
        // Given
        int totalPaidAmount = 0;

        /// When
        UserGradeEnum userGrade = this.userService.getUserGradeByTotalPaidPrice(totalPaidAmount);

        // Then
        assertEquals(userGrade, UserGradeEnum.FirstGrade);
    };

    @Test
    public void testGetUserGradeByTotalPaidPriceSecondGrade() {
        int totalPaidAmount = 100000;
        UserGradeEnum userGrade = this.userService.getUserGradeByTotalPaidPrice(totalPaidAmount);
        assertEquals(userGrade, UserGradeEnum.SecondGrade);
    };

    @Test
    public void testGetUserGradeByTotalPaidPriceThirdGrade() {
        int totalPaidAmount = 1000000;
        UserGradeEnum userGrade = this.userService.getUserGradeByTotalPaidPrice(totalPaidAmount);
        assertEquals(userGrade, UserGradeEnum.ThirdGrade);
    };

    @Test
    public void testGetUserGradeByTotalPaidPriceFourthGrade() {
        int totalPaidAmount = 3000000;
        UserGradeEnum userGrade = this.userService.getUserGradeByTotalPaidPrice(totalPaidAmount);
        assertEquals(userGrade, UserGradeEnum.FourthGrade);
    };

    @Test
    public void testGetUserGradeByTotalPaidPriceTopTier() {
        int totalPaidAmount = 10000000;
        UserGradeEnum userGrade = this.userService.getUserGradeByTotalPaidPrice(totalPaidAmount);
        assertEquals(userGrade, UserGradeEnum.TopTier);
    };
}
