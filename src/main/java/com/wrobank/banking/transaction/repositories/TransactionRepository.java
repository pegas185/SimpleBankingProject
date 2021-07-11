package com.wrobank.banking.transaction.repositories;

import com.wrobank.banking.transaction.domain.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAllByAccountId(Integer accountId);
}
