package com.dbr.rent.Controller;

import com.dbr.rent.Model.User;
import com.dbr.rent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentController {
    @Autowired
    RentService rentService;


    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody User user) throws Exception{
        return rentService.createUser(user);
    }
}
