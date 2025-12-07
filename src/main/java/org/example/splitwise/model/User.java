package org.example.splitwise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "splitwise_user")
public class User extends BaseModel {
    private String name;
    @Column(unique = true)
    private String emailAddress;
}
