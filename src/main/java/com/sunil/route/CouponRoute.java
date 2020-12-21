package com.sunil.route;

import com.sunil.service.CouponService;
import com.sunil.service.IssuedCouponService;
import com.sunil.model.Coupon;
import com.sunil.model.IssuedCoupon;
import com.sunil.vo.CouponRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupon")
public class CouponRoute {
    private final CouponService couponService;
    private final IssuedCouponService issuedCouponService;

    @Autowired
    public CouponRoute(CouponService couponService, IssuedCouponService issuedCouponService) {
        this.couponService = couponService;
        this.issuedCouponService = issuedCouponService;
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

    @PostMapping("/{couponId}/issue")
    public int issueCoupon(@PathVariable(value = "couponId") String couponId,
                           @RequestParam(value = "userId") String userId) throws Exception {
          return this.issuedCouponService.issueCoupon(Integer.parseInt(couponId), Integer.parseInt(userId));
    };
    
    @GetMapping("/issued-coupon/{issuedCouponId}")
    @ResponseBody
    public IssuedCoupon getIssuedCoupon(@PathVariable(value = "issuedCouponId") String issuedCouponId) throws Exception{
        return this.issuedCouponService.issueCouponById(Integer.parseInt(issuedCouponId));
    };
}