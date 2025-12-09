package org.example.splitwise.services;

import org.example.splitwise.controllers.UserController;
import org.example.splitwise.model.Expense;
import org.example.splitwise.model.ExpenseUser;
import org.example.splitwise.model.Group;
import org.example.splitwise.model.User;
import org.example.splitwise.repository.ExpenseRepository;
import org.example.splitwise.repository.ExpenseUserRepository;
import org.example.splitwise.repository.GroupRepository;
import org.example.splitwise.repository.UserRepository;
import org.example.splitwise.strategy.SimplifiedSettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ExpenseUserRepository expenseUserRepository;
    private final ExpenseRepository expenseRepository;
    private final UserController userController;

    public SettleUpService(GroupRepository groupRepository, UserRepository userRepository, ExpenseUserRepository expenseUserRepository, ExpenseRepository expenseRepository, UserController userController) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
        this.expenseRepository = expenseRepository;
        this.userController = userController;
    }

    public List<Expense> settleUpGroup(Long groupId){
        Optional<Group> groupToSettle = groupRepository.findById(groupId);
        return SimplifiedSettleUpStrategy.settleUp(groupToSettle.get().getExpenses());
    }

    public List<Expense> settleUpUserInGroup(Long groupId, Long userId) {
        List<Expense> settlingUpAGroup = settleUpGroup(groupId);
        List<Expense> result = new ArrayList<>();
        User user = userRepository.findById(userId).get();
        for (Expense expense : settlingUpAGroup) {
            if (expense.getOwedBy().stream().anyMatch(expenseUser -> expenseUser.getUser().equals(user)) ||
                    expense.getPaidBy().stream().anyMatch(expenseUser -> expenseUser.getUser().equals(user))) {
                result.add(expense);
            }

        }
        return result;
    }
//
    public List<Expense> settleUpUser(Long userId){
        User user =  userRepository.findById(userId).get();
        List<ExpenseUser> expenseUsers = expenseUserRepository.getAllByUser(user);
        List<Expense> expenses = expenseRepository.findAllByPaidByInOrOwedByIn(expenseUsers, expenseUsers);
        return SimplifiedSettleUpStrategy.settleUp(expenses);
    }
}





// ABC -> 1200
// All owed 400

// XYZ -> 2000
// ABC <- 1000
// XYZ <- 500
// MNO <- 500

//ABC -> 1200 - 400 - 1000
//ABC -> 200

// XYZ -400 -500 + 2000
// XYZ <- 1100

// MNO -> 900