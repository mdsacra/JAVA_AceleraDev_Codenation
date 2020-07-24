package com.challenge.endpoints;

import com.challenge.endpoints.advice.ResourceNotFoundException;
import com.challenge.entity.User;
import com.challenge.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id){
        return new ResponseEntity<User>(this.userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acceleration")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> findUsers(@RequestParam(name = "accelerationName", required = false) String accelName,
                                                @RequestParam(name = "companyId", required = false) Long companyId) {

        List<User> users = Optional.ofNullable(accelName).isPresent() ? this.userService.findByAccelerationName(accelName)
                : (Optional.ofNullable(companyId).isPresent() ? this.userService.findByCompanyId(companyId) : new ArrayList<>());

        return new ResponseEntity<>(users, HttpStatus.OK);

    }
}