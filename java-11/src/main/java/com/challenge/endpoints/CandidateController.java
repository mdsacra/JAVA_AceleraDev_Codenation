package com.challenge.endpoints;


import com.challenge.dto.CandidateDTO;
import com.challenge.endpoints.advice.ResourceNotFoundException;
import com.challenge.entity.Candidate;
import com.challenge.entity.User;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @GetMapping("/{userId}/{companyId}/{accelerationId}")
    public ResponseEntity<CandidateDTO> findById(@PathVariable("userId") Long userId,
                                                 @PathVariable("companyId") Long companyId,
                                                 @PathVariable("accelerationId") Long accelerationId){

        Optional<Candidate> candidate = this.candidateService.findById(userId, companyId, accelerationId);
        return candidate.map(value -> new ResponseEntity<>(candidateMapper.map(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new CandidateDTO(), HttpStatus.OK));
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> findCandidates(@RequestParam(name = "companyId", required = false) Long companyId,
                                                             @RequestParam(name = "accelerationId", required = false) Long accelId){

        List<CandidateDTO> candidateDTOList = Optional.ofNullable(companyId).isPresent() ? this.candidateMapper.map(this.candidateService.findByCompanyId(companyId))
                : (Optional.ofNullable(accelId).isPresent() ? this.candidateMapper.map(this.candidateService.findByAccelerationId(accelId)) : new ArrayList<>());

        return new ResponseEntity<>(candidateDTOList, candidateDTOList.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


}