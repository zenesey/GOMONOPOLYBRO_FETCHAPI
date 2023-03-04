package com.alex.crudapponboot.models;

public enum TestEnum {

    ABC,
    ASDASD;


    static TestEnum a(int a) {
        if (a == 1) {
            return ABC;

        }
        return null;
    }
}

