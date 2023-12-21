package com.green.greengram3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class Exam2 {
    @AfterEach
    public void afterEach() {
        System.out.println("afterEach");
    }


    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }
    @AfterAll
    public static void AfterAll() {
        System.out.println("AfterAll");
    }
    @Test
    public void test1() {
        System.out.println("dddddd");
    }

    @Test
    public void test2() {
        System.out.println("ad12312312");
    }


}
