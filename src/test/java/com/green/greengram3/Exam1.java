package com.green.greengram3;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Exam1 {

    @Test
    @DisplayName("테스트1")
    public void test1() {
        System.out.println("test1");
        int sum = 2 + 2;
        Assertions.assertEquals(4, sum); //왼쪽, 오른쪽 값 비교 (예상값, 실제값)
    }

    @Test
    public void test2() {
        System.out.println("test2");
        int multi = 2 * 3;
        Assertions.assertEquals(6, multi);
    }

    @Test
    public void test3() {
        Assertions.assertEquals(17, MyUtils.sum(11, 6), "1test");
        Assertions.assertEquals(17, MyUtils.sum(8, 9), "2test");
    }

    public void test4() {
        MyUtils utils = new MyUtils();
        Assertions.assertEquals(8, utils.multi(4, 2));
        Assertions.assertEquals(72, utils.multi(9, 8));
        Assertions.assertEquals(3, utils.multi(1, 3));
    }
}
