package com.rewards.RewardsProgram.Service;

import java.time.LocalDate;
import java.util.Map;

public interface RewardsService {

    int calculateRewardPoints(double purchaseAmount);

    Map<String, Integer> getRewardsByDateAndCustomer(LocalDate startDate, LocalDate endDate, Long customerId);
}
