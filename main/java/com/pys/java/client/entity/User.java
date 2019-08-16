package com.pys.java.client.entity;

import lombok.Data;

import java.util.Set;


@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String brief;
    private Set<String> userNames;
}

