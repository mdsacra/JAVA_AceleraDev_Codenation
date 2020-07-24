package com.challenge.endpoints;


import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private SubmissionMapper submissionMapper;

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> findSubmissionsByChallengeIdAndAccelerationId(@RequestParam(name = "accelerationId") Long accelerationId,
                                                                                  @RequestParam(name = "challengeId") Long challengeId){
        List<SubmissionDTO> submissionDTOList = submissionMapper
                .map(submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId));

        if (submissionDTOList.size() > 0) {
            return new ResponseEntity<List<SubmissionDTO>>(submissionDTOList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}