package org.example.splitwise.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class CreateGroupRequestDto {

    private String name;
    private boolean isSimplifiedDebtOn;
    private List<Long> userIds;
}
