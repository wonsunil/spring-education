package com.sunil.springeducation.service;

import com.sunil.springeducation.datamodel.SaleGroupByUserId;
import com.sunil.springeducation.datamodel.UserTotalPaidPrice;
import com.sunil.springeducation.model.Coupon;
import com.sunil.springeducation.model.IssuedCoupon;
import com.sunil.springeducation.model.Product;
import com.sunil.springeducation.model.Sale;
import com.sunil.springeducation.model.User;
import com.sunil.springeducation.repository.*;
import com.sunil.springeducation.datamodel.SaleStatus;
import com.sunil.springeducation.vo.SalePurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class SaleService {
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final IssuedCouponRepository issuedCouponRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository, UserRepository userRepository, ProductRepository productRepository, CouponRepository couponRepository, IssuedCouponRepository issuedCouponRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.couponRepository = couponRepository;
        this.issuedCouponRepository = issuedCouponRepository;
    };

    public Sale find(int saleId) throws Exception {
        Optional<Sale> searchedSale = this.saleRepository.findById(saleId);

        return searchedSale.orElseThrow(
                () -> new Exception("해당 판매기록을 찾을 수 없습니다")
        );
    };

    public List<Sale> findAll() {
        return this.saleRepository.findAll();
    };

    private int getDiscountAmount(int originAmount, int discountAmount, int discountPercentage) {
        if(discountAmount != 0) {
            return discountAmount;
        }else if(discountPercentage != 0) {
            return (int)Math.floor(originAmount + (discountPercentage/100));
        };

        return 0;
    };

    public int createSale(SalePurchaseVO salePurchaseVO) throws Exception {
        Optional<User> user = this.userRepository.findById(salePurchaseVO.getUserId());
        Optional<Product> product = this.productRepository.findById(salePurchaseVO.getProductId());

        Product findProduct = product.orElseThrow(() -> new Exception("존재하지 않는 상품입니다"));
        user.orElseThrow(() -> new Exception("존재하지 않는 유저입니다"));

        if(salePurchaseVO.getListPrice() != findProduct.getListPrice() * salePurchaseVO.getAmount()) {
            throw new Exception("정가가 상품정보에 등록된 가격과 다릅니다");
        };
        if(salePurchaseVO.getPaidPrice() != findProduct.getPrice() * salePurchaseVO.getAmount()) {
            throw new Exception("실제 구매가격이 상품정보에 등록된 가격과 다릅니다");
        };

        IssuedCoupon issuedCoupon = this.issuedCouponRepository.findById(salePurchaseVO.getIssuedCouponId())
                .orElseThrow(() -> new Exception("해당 ID로 발급된 쿠폰이 없습니다"));

        Coupon coupon = this.couponRepository.findById(issuedCoupon.getCouponId())
                .orElseThrow(() -> new Exception("해당 쿠폰이 없습니다"));

        int discountAmount = this.getDiscountAmount(salePurchaseVO.getPaidPrice(), coupon.getDiscountPrice(), coupon.getDiscountPercentage());

        Sale createSale = Sale.builder()
                .userId(salePurchaseVO.getUserId())
                .productId(salePurchaseVO.getProductId())
                .paidPrice(salePurchaseVO.getPaidPrice() - discountAmount)
                .listPrice(salePurchaseVO.getListPrice())
                .amount(salePurchaseVO.getAmount())
                .build();

        this.saleRepository.save(createSale);

        issuedCoupon.setUsed(true);
        this.issuedCouponRepository.save(issuedCoupon);

        this.saleRepository.flush();
        this.issuedCouponRepository.flush();

        return createSale.getSaleId();
    };

    public void purchase(int saleId) throws Exception {
        Optional<Sale> targetSale = this.saleRepository.findById(saleId);
        Sale sale = targetSale.orElseThrow(() -> new Exception("결제를 진행하는 도중에 문제가 발생했습니다!"));

        sale.setStatus(SaleStatus.PAID);
        this.saleRepository.save(sale);
        this.saleRepository.flush();
    };

    public void refund(int saleId) throws Exception {
        Optional<Sale> targetSale = this.saleRepository.findById(saleId);
        Sale sale = targetSale.orElseThrow(() -> new Exception("결제 취소를 진행하는 도중에 문제가 발생했습니다!"));

        sale.setStatus(SaleStatus.REFUNDED);
        this.saleRepository.save(sale);
        this.saleRepository.flush();
    };

    public void initializeSales() {
        Sale sale1 = Sale.builder()
                .userId(1)
                .productId(2)
                .paidPrice(25000)
                .listPrice(30000)
                .amount(1)
                .build();

        Sale sale2 = Sale.builder()
                .userId(2)
                .productId(1)
                .paidPrice(36000)
                .listPrice(15000)
                .amount(3)
                .build();

        Sale sale3 = Sale.builder()
                .userId(3)
                .productId(3)
                .paidPrice(150000)
                .listPrice(100000)
                .amount(2)
                .build();

        this.saleRepository.save(sale1);
        this.saleRepository.save(sale2);
        this.saleRepository.save(sale3);
        this.saleRepository.flush();
    };

    public List<Sale> findByUserId(int userId) {
        return this.saleRepository.findByUserId(userId);
    };

    public UserTotalPaidPrice getTotalPaidPriceByUserId(int userId) {
        SaleGroupByUserId groupData = this.saleRepository.PurchaseAmountGroupByUserId(userId);
        return new UserTotalPaidPrice(groupData);
    };
}
