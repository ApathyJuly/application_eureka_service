package com.example.service_hi.wisdom_hr.work_date.Test.yndw;

import com.example.service_hi.user.entity.User;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PmsWord {
    public static void main(String[] args) {
        User u1 = new User();
        u1.setSalt("1");
        User u2 = new User();
        u2.setSalt("1");
        User u3 = new User();
        u3.setSalt("1");
        List<User> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);
        System.out.println(list.contains(u1));
        Integer integer = list.stream()
                .map(
                        e -> Integer.valueOf(
                                Optional.ofNullable(e.getSalt()).orElse("0")
                        )
                ).reduce(Integer::sum).get();
        System.out.println(integer);
        //try-with-resource语法
        try (
                //使用的资源
                Scanner scanner = new Scanner(new File("H:\\TestProject\\src\\main\\java\\com\\chen\\test.txt"));
                )
        {
            // TODO: 2021/9/22

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
