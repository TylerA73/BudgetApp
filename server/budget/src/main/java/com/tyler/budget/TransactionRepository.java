package com.tyler.budget;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Repository("transactionRepository")
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
    @Query(value = "SELECT t.category_id, c.description, ROUND(SUM(t.transaction_amount), 2) FROM " +
                   "transactions t, categories c where t.category_id = c.category_id " +
                   "and t.transaction_date >= :from and t.transaction_date <= :to " +
                   "group by t.category_id", nativeQuery = true)
    public List<Object[]> sum(String from, String to);

    @Query(value = "SELECT * from transactions where transaction_date >= :from and transaction_date <= :to",
            nativeQuery = true)
    public List<Transaction> findTransactionsBetweenDates(String from, String to);
}
