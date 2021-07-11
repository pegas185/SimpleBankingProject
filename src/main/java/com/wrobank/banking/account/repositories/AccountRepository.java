package com.wrobank.banking.account.repositories;

import com.wrobank.banking.account.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    List<Account> findAllByCustomerId(Integer customerID);
}
