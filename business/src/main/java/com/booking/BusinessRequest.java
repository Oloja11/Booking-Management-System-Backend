package com.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class BusinessRequest {
    private String name;
    private String description;
    private String industry;
    private String email;
    private String password;
}
