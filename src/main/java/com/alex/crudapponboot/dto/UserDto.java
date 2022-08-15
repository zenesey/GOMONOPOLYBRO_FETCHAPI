package com.alex.crudapponboot.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
// ХАЙ БУДЭ

@Data
@AllArgsConstructor
public class UserDto {

    private long id;

    private String name;

    private String surName;

    private int age;

    private String username;

    private String password;



}
