package com.rewards.RewardsProgram.Repository;

import com.rewards.RewardsProgram.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByTransactionDateBetweenAndCustomerId(LocalDate startDate, LocalDate endDate, Long customerId);
}
