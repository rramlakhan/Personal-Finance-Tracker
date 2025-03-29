package com.personalFinanceTracker.controllers;

import com.personalFinanceTracker.entities.Transaction;
import com.personalFinanceTracker.services.TransactionService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.addTransaction(transaction);
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransactionById(@PathVariable ObjectId transactionId) {
        return transactionService.getTransactionById(transactionId);
    }

    @DeleteMapping("/{transactionId}")
    public boolean deleteTransaction(@PathVariable ObjectId transactionId) {
        return transactionService.deleteTransaction(transactionId);
    }
 }
