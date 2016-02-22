package com.civil.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zaza on 2015/5/2..
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findUserByLoiginIdAndPwd(String loginId, String pwd) {
        return userRepository.findUserByIdAndPwd(loginId, pwd);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long userId) {
        return userRepository.findOne(userId);
    }

    public boolean updateVerifyLevel(Long userId, Integer verifyLevel) {
        User user = findById(userId);
        if (user  != null) {
            user.setVerifiedLevel(verifyLevel);
        }
        save(user);
        return true;
    }

    public boolean updatePassword(Long userId, String password) {
        User user = findById(userId);
        if (user  != null) {
            user.setPassword(password);
        }
        save(user);
        return true;
    }
}
