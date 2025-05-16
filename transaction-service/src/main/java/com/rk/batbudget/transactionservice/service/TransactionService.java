package com.rk.batbudget.transactionservice.service;

import com.rk.batbudget.transactionservice.model.Transaction;
import org.springframework.stereotype.Service;
import com.rk.batbudget.transactionservice.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
