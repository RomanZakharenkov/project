package by.rzmarket.service;

import by.rzmarket.dao.UserDao;
import by.rzmarket.dto.LoginUserDto;
import by.rzmarket.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    public User getById(Integer id) {
        return UserDao.getInstance().getById(id).get();
    }

    public boolean update(User user) {
        return UserDao.getInstance().update(user);
    }

    public boolean isValid(LoginUserDto loginUserDto) {
        User user = User.builder()
                .email(loginUserDto.getEmail())
                .password(loginUserDto.getPassword())
                .build();
        return UserDao.getInstance().isValid(user);
    }

    public List<User> getAll() {
        return UserDao.getInstance().getAll();
    }

    public Integer add(User user) {
        return UserDao.getInstance().add(user);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
