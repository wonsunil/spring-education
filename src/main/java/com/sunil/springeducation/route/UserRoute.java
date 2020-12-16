package com.sunil.springeducation.route;

import com.sunil.springeducation.datamodel.UserGradeEnum;
import com.sunil.springeducation.datamodel.UserTotalPaidPrice;
import com.sunil.springeducation.model.Sale;
import com.sunil.springeducation.model.User;
import com.sunil.springeducation.service.SaleService;
import com.sunil.springeducation.service.UserService;
import com.sunil.springeducation.vo.UserRegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRoute {
    private final UserService userService;
    private final SaleService saleService;

    @Autowired
    public UserRoute(UserService userService, SaleService saleService) {
        this.userService = userService;
        this.saleService = saleService;
    };

    @GetMapping("")
    @ResponseBody
    public List<User> getUsers() {
        return this.userService.findAll();
    };

    @GetMapping("/{userId}")
    @ResponseBody
    public User getUser(@PathVariable(value = "userId") String userId) throws Exception{
        return this.userService.find(Integer.parseInt(userId));
    };

    @PostMapping("")
    public int createUser(UserRegisterVO user) {
        return this.userService.createUser(user);
    };

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable(value = "userId") String userId) {
        this.userService.deleteUser(Integer.parseInt(userId));
    };

    @GetMapping("/initialize")
    public void initializers() {
        this.userService.initializeUsers();
    };

    @GetMapping("/{userId}/purchase_list")
    public List<Sale> userPurchaseList(@PathVariable(value = "userId") String userId) {
        return this.saleService.findByUserId(Integer.parseInt(userId));
    };

    @GetMapping("/{userId}/purchase_amount")
    public UserTotalPaidPrice userPurchaseAmount(@PathVariable(value = "userId") String userId) {
        return this.saleService.getTotalPaidPriceByUserId(Integer.parseInt(userId));
    };

    @GetMapping("/{userId}/grade")
    @ResponseBody
    public UserGradeEnum userGrade(@PathVariable(value = "userId") String userId) {
        return this.userService.getUserGrade(Integer.parseInt(userId));
    };
}
