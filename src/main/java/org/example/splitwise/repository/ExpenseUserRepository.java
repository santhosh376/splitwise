package org.example.splitwise.repository;

import org.example.splitwise.model.ExpenseUser;
import org.example.splitwise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseUserRepository extends JpaRepository<ExpenseUser,Long> {

    public List<ExpenseUser> getAllByUser(User user);
}
