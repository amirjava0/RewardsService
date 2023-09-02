package com.rewards.RewardsProgram.Service;

import com.rewards.RewardsProgram.Entity.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    List<Transaction> findTransactionsByCustomerIdAndStartAndEndDate(LocalDate startDate, LocalDate endDate, Long customerId);
}
