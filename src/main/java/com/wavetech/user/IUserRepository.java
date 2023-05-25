package com.wavetech.user;

import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<UserModel, Integer> {

}
