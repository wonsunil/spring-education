package com.sunil.springeducation.route;

import com.sunil.springeducation.datamodel.dto.SaleDTO;
import com.sunil.springeducation.model.Sale;
import com.sunil.springeducation.service.SaleService;
import com.sunil.springeducation.datamodel.vo.SalePurchaseVO;
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
    public List<SaleDTO> getSales() {
        return this.saleService.sales();
    };

    @GetMapping("/{saleId}")
    @ResponseBody
    public SaleDTO getSale(@PathVariable(value = "saleId") String saleId) throws Exception{
        return this.saleService.saleById(Integer.parseInt(saleId));
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

    public List<SaleDTO> getSalesByUserId(int userId) {
        return this.saleService.findByUserId(userId);
    };
}
