package com.appfacturasya.resource;

import lombok.Data;

@Data
public class SaveUserResource {

    private String username;
    private String password;
    private String email;
    private String name;
}
