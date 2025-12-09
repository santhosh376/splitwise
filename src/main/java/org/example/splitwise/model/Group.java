package org.example.splitwise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "splitwise_group")
public class Group extends BaseModel {
    private String name;
    @ManyToMany
    private List<User> users;

    @OneToMany(mappedBy = "group")
    private List<Expense> expenses;

    private boolean isSimplifiedDebtOn;
}
