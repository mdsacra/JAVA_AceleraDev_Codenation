package com.challenge.endpoints;

import com.challenge.endpoints.advice.ResourceNotFoundException;
import com.challenge.entity.Company;
import com.challenge.entity.User;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable("id") Long id){
        return new ResponseEntity<Company>(this.companyService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Company>> findCompanies(@RequestParam(name = "accelerationId", required = false) Long accelId,
                                                       @RequestParam(name = "userId", required = false) Long userId){
        List<Company> companies = Optional.ofNullable(accelId).isPresent() ? this.companyService.findByAccelerationId(accelId)
                : (Optional.ofNullable(userId).isPresent() ? this.companyService.findByUserId(userId) : new ArrayList<>());

        return new ResponseEntity<>(companies, companies.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


}