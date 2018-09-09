package by.rzmarket;

import by.rzmarket.dao.UserDao;
import by.rzmarket.entity.Role;
import by.rzmarket.entity.User;

public class test {

    public static void main(String[] args) {

        System.out.println(UserDao.getInstance().getById(1));
//        User user = User.builder()
//                .roleId(Role.builder()
//                        .id(2)
//                        .name("USER")
//                        .build())
//                .firstName("Сергей")
//                .lastName("Сергеев")
//                .email("sergey05@gmail.com")
//                .password("050585sergey")
//                .number("375441254411")
//                .build();
//        UserDao.getInstance().add(user);
//        System.out.println(user);
    }
}
