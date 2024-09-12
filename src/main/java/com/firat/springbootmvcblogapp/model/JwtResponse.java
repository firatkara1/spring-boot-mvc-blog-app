package com.firat.springbootmvcblogapp.model;

import com.firat.springbootmvcblogapp.dto.UserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponse {

    private String jwtToken;
    private String username;

    private UserDto userDto;
}
