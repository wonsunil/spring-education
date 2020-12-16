package com.sunil.springeducation.route;

import com.sunil.springeducation.model.Sale;
import com.sunil.springeducation.model.User;
import com.sunil.springeducation.service.SaleService;
import com.sunil.springeducation.vo.SalePurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sale")
public class SaleRoute {
    private final SaleService saleService;

    @Autowired
    public SaleRoute(SaleService saleService) {
        this.saleService = saleService;
    };

    @GetMapping("")
    @ResponseBody
    public List<Sale> getSales() {
        return this.saleService.findAll();
    };

    @GetMapping("/{saleId}")
    @ResponseBody
    public Sale getSale(@PathVariable(value = "saleId") String saleId) throws Exception{
        return this.saleService.find(Integer.parseInt(saleId));
    };

    @GetMapping("/initialize")
    public void initializers() {
        this.saleService.initializeSales();
    };

    @PostMapping("/purchase")
    public void purchase(SalePurchaseVO sale) throws Exception {
        int saleId = this.saleService.createSale(sale);
        this.saleService.purchase(saleId);
    };

    @PostMapping("/{saleId}/refund")
    public void refund(@PathVariable(value = "saleId") String saleId) throws Exception{
        this.saleService.refund(Integer.parseInt(saleId));
    };
}
