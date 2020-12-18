package com.sunil.springeducation.route;

import com.sunil.springeducation.model.Coupon;
import com.sunil.springeducation.service.CouponService;
import com.sunil.springeducation.vo.CouponRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponRoute {
    private final CouponService couponService;

    @Autowired
    public CouponRoute(CouponService couponService) {
        this.couponService = couponService;
    };

    @GetMapping("/{couponId}")
    @ResponseBody
    public Coupon getCoupon(@PathVariable(value = "couponId") String couponId) throws Exception {
        return this.couponService.couponById(Integer.parseInt(couponId));
    };

    @PostMapping
    public int creatCoupon(CouponRegisterVO coupon) throws Exception {
        return this.couponService.createCoupon(coupon);
    };
}
