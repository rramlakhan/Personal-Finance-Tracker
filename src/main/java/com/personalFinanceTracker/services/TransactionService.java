package com.personalFinanceTracker.services;

import com.personalFinanceTracker.entities.Transaction;
import com.personalFinanceTracker.entities.User;
import com.personalFinanceTracker.repositories.TransactionRepository;
import com.personalFinanceTracker.repositories.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public Transaction addTransaction(Transaction transaction) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        transaction.setUser(user);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        return transactionRepository.findByUser(user);
    }

    public Transaction getTransactionById(ObjectId transactionId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        Optional<Transaction> transactionOpt = transactionRepository.findById(transactionId);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            if (transaction.getUser().getId().equals(user.getId())) {
                return transaction;
            }
        }
        return null;
    }

    public boolean deleteTransaction(ObjectId transactionId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.findByEmail(email);
        Optional<Transaction> transactionOpt = transactionRepository.findById(transactionId);
        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            if (transaction.getUser().getId().equals(user.getId())) {
                transactionRepository.delete(transaction);
                return true;
            }
        }
       return false;
    }
}
