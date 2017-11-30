package com.university.www.repository;

import com.university.www.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
    Account findByUsername(String username);
}
