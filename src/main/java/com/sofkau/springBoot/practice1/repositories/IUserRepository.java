package com.sofkau.springBoot.practice1.repositories;

import com.sofkau.springBoot.practice1.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends CrudRepository<UserModel,Long> {
    public List<UserModel> findByPriority(Integer priority);
}
