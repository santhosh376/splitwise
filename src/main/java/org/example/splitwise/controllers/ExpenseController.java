package org.example.splitwise.controllers;

import org.example.splitwise.dtos.AddExpenseRequestDto;
import org.example.splitwise.repository.ExpenseRepository;
import org.example.splitwise.services.ExpenseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping(path = "/add")
    public @ResponseBody Long addExpense(@RequestBody AddExpenseRequestDto request){
       return expenseService.createExpense(request).getId();
    }
}
