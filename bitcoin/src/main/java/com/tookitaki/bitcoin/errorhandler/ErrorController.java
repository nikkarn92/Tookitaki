package com.tookitaki.bitcoin.errorhandler;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    //private final ErrorAttributes errorAttributes;



    @Override
    public String getErrorPath() {
        return "/error";
    }

}
