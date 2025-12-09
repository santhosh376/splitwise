package org.example.splitwise.services;

import org.example.splitwise.dtos.AddExpenseRequestDto;
import org.example.splitwise.model.Expense;
import org.example.splitwise.model.ExpenseType;
import org.example.splitwise.model.ExpenseUser;
import org.example.splitwise.model.User;
import org.example.splitwise.repository.ExpenseRepository;
import org.example.splitwise.repository.ExpenseUserRepository;
import org.example.splitwise.repository.GroupRepository;
import org.example.splitwise.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService{
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseUserRepository expenseUserRepository;

    public ExpenseService(UserRepository userRepository, GroupRepository groupRepository, ExpenseRepository expenseRepository, ExpenseUserRepository expenseUserRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
        this.expenseUserRepository = expenseUserRepository;
    }

    public Expense createExpense(AddExpenseRequestDto request){

        //create expense users
        Map<Long,Double> paidBy = request.getPaidBy();
        List<ExpenseUser> paidByExpenseUser = new ArrayList<>();
        paidBy.forEach((userId,amount) -> {
            User user = userRepository.findById(userId).get();
            ExpenseUser expenseUser = expenseUserRepository.save(new ExpenseUser(user,amount));
            paidByExpenseUser.add(expenseUser);
        });

        Map<Long,Double> owedBy = request.getOwedBy();
        List<ExpenseUser> owedByExpenseUser = new ArrayList<>();
        owedBy.forEach((userId,amount) -> {
            User user = userRepository.findById(userId).get();
            ExpenseUser expenseUser = expenseUserRepository.save(new ExpenseUser(user,amount));
            owedByExpenseUser.add(expenseUser);
        });

        Expense expense = Expense.builder()
                .group(groupRepository.findByName(request.getGroupName()).get())
                .expenseType(request.getExpenseType())
                .name(request.getName())
                .paidBy(paidByExpenseUser)
                .owedBy(owedByExpenseUser)
                .totalAmount(request.getTotalAmount())
                .build();

        expenseRepository.save(expense);

        return expense;
    }

}
