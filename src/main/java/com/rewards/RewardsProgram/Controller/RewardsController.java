package com.rewards.RewardsProgram.Controller;

import com.rewards.RewardsProgram.Service.RewardsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardsController {

    private RewardsService rewardsService;


    public RewardsController(RewardsService rewardsService) {

        this.rewardsService = rewardsService;
    }

    @GetMapping("/calculate")
    public ResponseEntity calculateRewardPoints(@RequestParam final Long customerId,
                                                @RequestParam
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                final LocalDate startDate,
                                                @RequestParam
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                    final LocalDate endDate) {

        final Map<String, Integer> rewardsPerMonth = rewardsService
                .getRewardsByDateAndCustomer(startDate, endDate, customerId);

        return ResponseEntity.status(HttpStatus.OK).body(rewardsPerMonth);
    }
}
