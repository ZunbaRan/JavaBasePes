package com.satomi.alltest.javaBaseLearning._09JUC;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-05
 */
public class _09stream {
    @Test
    public void test01(){
        User u1 = new User(1, "a", 21) ;
        User u2 = new User(2, "b", 22) ;
        User u3 = new User(3, "c", 23) ;
        User u4 = new User(4, "d", 24) ;

        List<User> list = Arrays.asList(u1, u2, u3, u4) ;

        //计算叫个Stream流
        list.stream().filter(u -> {return  u.getId() % 2 == 0;}).forEach(System.out :: println);

    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private int id ;
    private String name ;
    private int age ;
}