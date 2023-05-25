package com.wavetech;

import com.wavetech.user.IUserRepository;
import com.wavetech.user.UserModel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private IUserRepository repo;

    @Test
    public void testAddNew() {
        UserModel user = new UserModel();
        user.setEmail("joao.della@gmail.com");
        user.setPassword("joao123456");
        user.setFirstName("Joao");
        user.setLastName("Della");

        UserModel savedUser = repo.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<UserModel> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for( UserModel user : users){
            System.out.println(user);
        }
    }

    @Test
    public void testGet(){
        Integer userId = 1;
        Optional<UserModel> optionalUser = repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();

        System.out.println(optionalUser.get());
    }
}
