package com.challenge.endpoints;

import com.challenge.endpoints.advice.ResourceNotFoundException;
import com.challenge.entity.Acceleration;
import com.challenge.service.impl.AccelerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

    @Autowired
    private AccelerationService acceleration;

    @GetMapping("/{id}")
    public ResponseEntity<Acceleration> findById(@PathVariable("id") Long id){
        return new ResponseEntity<Acceleration>(this.acceleration.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acceleration")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Acceleration>> findAccelerationsByCompanyId(@RequestParam(name = "companyId") Long companyId){
        if (this.acceleration.findByCompanyId(companyId).size() > 0){
            return new ResponseEntity<List<Acceleration>>(this.acceleration.findByCompanyId(companyId), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Acceleration>>(HttpStatus.NO_CONTENT);
        }
    }
}
