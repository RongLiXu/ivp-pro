package com.xunyat.iot.vmp.web.gb28181;

import com.xunyat.iot.vmp.service.IUserService;
import com.xunyat.iot.vmp.storager.dao.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/login")
    public String devices(String name, String passwd){
        User user = userService.getUser(name, passwd);
        if (user != null) {
            return "success";
        }else {
            return "fail";
        }
    }
}
