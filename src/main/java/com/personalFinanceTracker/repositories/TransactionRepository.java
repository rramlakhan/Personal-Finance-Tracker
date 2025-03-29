package com.personalFinanceTracker.repositories;

import com.personalFinanceTracker.entities.Transaction;
import com.personalFinanceTracker.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, ObjectId> {
    List<Transaction> findByUser(User user);
}
