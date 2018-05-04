package io.github.frapples.osbrainsystem.biz.model;

import javax.validation.constraints.AssertTrue.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private String name;
    private String email;
    private String phone;
    private String password;
    private String passwordHash;
}
