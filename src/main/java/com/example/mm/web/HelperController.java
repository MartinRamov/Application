package com.example.mm.web;

import com.example.mm.model.User;
import com.example.mm.service.impl.PaypalServiceImpl;
import com.paypal.api.payments.Address;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/other", produces = "application/json")
public class HelperController {

    @Autowired
    private PaypalServiceImpl paypalService;

    @RequestMapping(value = "/getTime", method = RequestMethod.GET)
    public String getServerTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    @RequestMapping(value = "/testPaypal", method = RequestMethod.GET)
    public List<Payment> paymentList() {

        User user = new User();
        user.firstName = "Name";
        user.lastName = "Last";
        user.email = "name.last@test.com";

        Address address = new Address();
        address.setCity("Mobile");
        address.setCountryCode("US");
        address.setLine1("4168 O Conner Street");
        address.setPostalCode("36575");
        address.setState("AL");

        CreditCard creditCard = new CreditCard();
        creditCard.setBillingAddress(address);
        creditCard.setCvv2(111);
        creditCard.setExpireMonth(9);
        creditCard.setExpireYear(2022);
        creditCard.setFirstName("John");
        creditCard.setLastName("Fendley");
        creditCard.setNumber("4032036810992006");
        creditCard.setType("visa");

        List<Payment> payments = paypalService.executeCreditCardPayment(creditCard, user);
        return payments != null && payments.size() > 0? payments : null;
    }
}
