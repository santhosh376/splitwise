package org.example.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "splitwise_group")
public class Group extends BaseModel {
    private String name;
    @ManyToMany
    private List<User> users;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;

    private boolean isSimplifiedDebtOn;
}
