package com.example.mm.web;

import com.example.mm.model.User;
import com.example.mm.service.UserService;
import com.example.mm.service.impl.PaypalServiceImpl;
import com.paypal.api.payments.Address;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/paypal", produces = "application/json")
public class PaypalController {

    private static final Logger logger = LoggerFactory.getLogger(PaypalController.class);

    private PaypalServiceImpl paypalService;

    private UserService userService;

    @Autowired
    public PaypalController(PaypalServiceImpl paypalService, UserService userService) {
        this.paypalService = paypalService;
        this.userService = userService;
    }

    @RequestMapping(value = "/buyAccount", method = RequestMethod.POST)
    public ResponseEntity<?> buyPremiumAccount(@RequestParam Long userId, @RequestParam String city,
                                               @RequestParam String countryCode, @RequestParam String homeAddress,
                                               @RequestParam String postalCode, @RequestParam String state,
                                               @RequestParam Integer expireMonth, @RequestParam Integer expireYear,
                                               @RequestParam String firstName, @RequestParam String lastName,
                                               @RequestParam String cardNumber, @RequestParam String cardType) {

        User user = userService.getUserById(userId);
        if (user == null || user.isPremiumUser) {
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }

        Address address = new Address();
        address.setCity(city);
        address.setCountryCode(countryCode);
        address.setLine1(homeAddress);
        address.setPostalCode(postalCode);
        address.setState(state);

        CreditCard creditCard = new CreditCard();
        creditCard.setBillingAddress(address);
        creditCard.setCvv2(111);
        creditCard.setExpireMonth(expireMonth);
        creditCard.setExpireYear(expireYear);
        creditCard.setFirstName(firstName);
        creditCard.setLastName(lastName);
        creditCard.setNumber(cardNumber);
        creditCard.setType(cardType);

        List<Payment> payments = paypalService.executeCreditCardPayment(creditCard, user);

        if (payments != null && payments.size() > 0) {
            logger.info(payments.toString());
            user.isPremiumUser = true;
            user = userService.makeUserPremium(user.id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.PAYMENT_REQUIRED);
    }

}
