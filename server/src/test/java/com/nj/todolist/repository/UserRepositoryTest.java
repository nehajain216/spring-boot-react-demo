package com.nj.todolist.repository;

import com.nj.todolist.common.PostgreSQLContainerInitializer;
import com.nj.todolist.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = PostgreSQLContainerInitializer.class)
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void shouldGetValueFromDatabase() {
        User appUser = new User("neha", "neha123");
        User tempAppUser = new User("neha","neha123");

        userRepository.save(appUser);
        Optional<User> resultUser = userRepository.findOne(Example.of(tempAppUser));

        assertThat(resultUser.isEmpty(), is(false));
        assertThat(resultUser.get(), is(appUser));
    }
}