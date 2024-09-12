package com.firat.springbootmvcblogapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
public class UserDto {

    private int id;

    @NotEmpty
    @Size(min = 5, message = "Kullanici adi en az 5 karakterden olusmali!")
    @Column(unique = true)
    private String name;

    @Email(message = "Email geçerli değil!")
    @NotEmpty(message = "Email gereklidir!")
    @Column(unique = true)
    private String email;

    @NotEmpty
    @Size(min = 5, message = "Sifre en az 5 karakterden olusmali")
    private String password;

    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password=password;
    }


    //@NotEmpty
    private String about;
}
