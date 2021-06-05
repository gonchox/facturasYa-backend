package com.appfacturasya.resource;

import lombok.Data;

@Data
public class UserResource {
    private Long id;
    private String password;
    private String username;
    private String email;
    private String name;

}
