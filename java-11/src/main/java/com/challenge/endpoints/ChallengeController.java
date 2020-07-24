package com.challenge.endpoints;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {

    @Autowired
    private ChallengeService challenge;

    @GetMapping
    public ResponseEntity<List<Challenge>> findChallengesByAccelerationIdAndUserId(@RequestParam(name = "accelerationId") Long accelerationId,
                                                                                   @RequestParam(name = "userId") Long userId) {
        List<Challenge> challenges = this.challenge.findByAccelerationIdAndUserId(accelerationId, userId);
        if (challenges.size() > 0){
            return new ResponseEntity<List<Challenge>>(this.challenge.findByAccelerationIdAndUserId(accelerationId, userId),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


}