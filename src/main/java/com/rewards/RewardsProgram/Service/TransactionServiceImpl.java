package com.rewards.RewardsProgram.Service;

import com.rewards.RewardsProgram.Entity.Transaction;
import com.rewards.RewardsProgram.Repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionServiceImpl implements  TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public List<Transaction> findTransactionsByCustomerIdAndStartAndEndDate(final LocalDate startDate,
                                                                            final LocalDate endDate,
                                                                            final Long customerId){
       final List<Transaction> transactions = transactionRepository
               .findByTransactionDateBetweenAndCustomerId(startDate, endDate, customerId);

       return transactions;
    }
}
