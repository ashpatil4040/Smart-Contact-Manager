package com.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserFrom {

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must have min 3 characters")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must have min 6 characters")
    private String password;

    @NotBlank(message = "About is required")
    private String about;

    @Size(min= 8, max = 12, message = "Phone number must have 8 to 12 digits")
    private String phoneNumber;
}
