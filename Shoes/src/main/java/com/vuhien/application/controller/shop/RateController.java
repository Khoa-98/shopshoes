//package com.vuhien.application.controller.shop;
//
//import com.vuhien.application.entity.Rate;
//import com.vuhien.application.entity.User;
//import com.vuhien.application.model.request.CreateRateProductRequest;
//import com.vuhien.application.security.CustomUserDetails;
//import com.vuhien.application.service.RateService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//@Controller
//public class RateController {
//
//    @Autowired
//    private RateService rateService;
//
//    @PostMapping("/api/rates/product")
//    public ResponseEntity<Object> createRate(@RequestBody CreateRateProductRequest createRateProductRequest){
//        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
//        Rate rate = rateService.createRate(createRateProductRequest,user.getId());
//        return ResponseEntity.ok(rate);
//    }
//
//}
