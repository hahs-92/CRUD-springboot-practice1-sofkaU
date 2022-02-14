package com.sofkau.springBoot.practice1.services;

import com.sofkau.springBoot.practice1.models.UserModel;
import com.sofkau.springBoot.practice1.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAll() {
        return (List<UserModel>) userRepository.findAll();
    }

    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public Optional<UserModel> getById(Long id) {
        return userRepository.findById(id);
    }

    public List<UserModel> getByPriority(Integer priority) {
        return userRepository.findByPriority(priority);
    }

}
