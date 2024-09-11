package com.vvs.cm.service;

import com.vvs.cm.model.User;
import com.vvs.cm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers(String username) {
        List<User> users = new ArrayList<User>();

        if (username == null)
            userRepository.findAll().forEach(users::add);
        else
            userRepository.findByUserNameContaining(username).forEach(users::add);
        return users;

    }

    public User getUserById(long id) {
        Optional<User> UserData = userRepository.findById(id);
        return UserData.get();
    }

    public User createUser(User User) {
        User _User = userRepository
                .save(User);
        return _User;
    }

    public User updateUser(long id, User User) {
        Optional<User> UserData = userRepository.findById(id);

        if (UserData.isPresent()) {
            User _User = UserData.get();
            _User.setUserName(User.getUserName());
            _User.setFirstName(User.getFirstName());
            _User.setActive(User.isActive());
            return userRepository.save(_User);

        }
        return null;
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();


    }

    public List<User> findByPublished() {
        List<User> users = userRepository.findByActive(true);
        return users;
    }

}
