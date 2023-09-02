package com.rewards.RewardsProgram.Service;

import com.rewards.RewardsProgram.Entity.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardsServiceImpl implements  RewardsService {

    private TransactionService transactionService;

    public RewardsServiceImpl(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    public int calculateRewardPoints(final double purchaseAmount) {
        int points = 0;

        if (purchaseAmount > 100) {
            points += 2 * (int)(purchaseAmount - 100);
        }
        if (purchaseAmount > 50) {
            points += (int)(purchaseAmount - 50);
        }

        return points;
    }

    public Map<String, Integer>  getRewardsByDateAndCustomer(final LocalDate startDate,
                                                             final LocalDate endDate,
                                                             final Long customerId) {

        final List<Transaction> transactions = transactionService.
                findTransactionsByCustomerIdAndStartAndEndDate(startDate, endDate, customerId);

        final Map<String, Integer> rewardsPerMonth = new HashMap<>();

        for (final Transaction transaction : transactions) {
                final int rewardPoints = calculateRewardPoints(transaction.getPurchaseAmount());
                final String monthKey = transaction.getTransactionDate().getMonth().toString();
                rewardsPerMonth.put(monthKey, rewardsPerMonth.getOrDefault(monthKey, 0) + rewardPoints);
        }

        final int total = rewardsPerMonth.values().stream().mapToInt(Integer::intValue).sum();
        rewardsPerMonth.put("total",total);

        return  rewardsPerMonth;
    }
}
