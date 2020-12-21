package com.sunil.test;

import com.sunil.springeducation.repository.CouponRepository;
import com.sunil.springeducation.service.CouponService;
import com.sunil.springeducation.datamodel.vo.CouponRegisterVO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CouponServiceTests {
    private final CouponService couponService;
    private final CouponRepository couponRepository;

    public CouponServiceTests(CouponService couponService, CouponRepository couponRepository) {
        this.couponRepository = mock(CouponRepository.class);
        this.couponService = new CouponService(couponRepository);
    }

    @Test
    @Transactional
    public void testCreateCouponWhenPercentageAndPriceExists() {
        // given
        CouponRegisterVO couponRegisterVO = new CouponRegisterVO(new Date(), 7, 1,
                "null", 1000, 10);

        // when
        Exception thrown = assertThrows(Exception.class, () -> this.couponService.createCoupon(couponRegisterVO));

        // then
        assertEquals("할인 금액과 할인 비율이 동시에 존재 할 수 없습니다!", thrown.getMessage());
    };

    @Test
    @Transactional
    public void testCreateCouponWhenNormal() throws Exception {
        // given
        CouponRegisterVO couponRegisterVO = new CouponRegisterVO(new Date(), 7, 1,
                "null", 1000, 0);

        // when
        this.couponService.createCoupon(couponRegisterVO);

        // then
        verify(this.couponRepository).flush();
    };
}
