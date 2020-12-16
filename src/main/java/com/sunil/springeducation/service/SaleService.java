package com.sunil.springeducation.service;

import com.sunil.springeducation.datamodel.SaleStatus;
import com.sunil.springeducation.model.Sale;
import com.sunil.springeducation.repository.SaleRepository;
import com.sunil.springeducation.vo.SalePurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class SaleService {
    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
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

    public int createSale(SalePurchaseVO sale) {
        Sale createSale = Sale.builder()
                .userId(sale.getUserId())
                .productId(sale.getProductId())
                .paidPrice(sale.getPaidPrice())
                .listPrice(sale.getListPrice())
                .amount(sale.getAmount())
                .build();

        this.saleRepository.save(createSale);
        this.saleRepository.flush();

        return createSale.getSaleId();
    };

    public void purchase(int saleId) throws Exception {
        Optional<Sale> targetSale = this.saleRepository.findById(saleId);
        Sale sale = targetSale.orElseThrow(() -> new Exception("결제를 진행하는 도중에 문제가 발생했습니다!"));

        sale.setStatus(SaleStatus.PAID);
        this.saleRepository.save(sale);
        this.saleRepository.flush();
    };

    public void refund(int orderId) {

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
}
