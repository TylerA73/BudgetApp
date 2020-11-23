package com.tyler.budget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping(path="/add")
    public @ResponseBody Transaction register(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);

        return transaction;
    }

    @GetMapping(path="/{id}")
    public @ResponseBody Optional<Transaction> getUser(@PathVariable Long id) {
        return transactionRepository.findById(id);
    }

    @GetMapping(path = "/amounts")
    public @ResponseBody List<Amount> getAmounts(@RequestParam String from, @RequestParam String to) {
        List<Amount> amounts = new ArrayList<Amount>(0);
        transactionRepository.sum(from, to).stream().forEach((amount) -> {
            Category category = new Category();
            category.setId((Integer)amount[0]);
            category.setDescription((String)amount[1]);
            amounts.add(new Amount((BigDecimal) amount[2], category));
        });
        return amounts;
    }

    @GetMapping(path = "/dates")
    public @ResponseBody List<Transaction> getTransactions(@RequestParam String from, @RequestParam String to) {
        return transactionRepository.findTransactionsBetweenDates(from, to);
    }

}
