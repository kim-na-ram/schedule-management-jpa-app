package com.bootcamp.schedulemanagementjpaapp.repository;

import com.bootcamp.schedulemanagementjpaapp.common.exception.ApiException;
import com.bootcamp.schedulemanagementjpaapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static com.bootcamp.schedulemanagementjpaapp.common.enums.ResponseCode.NOT_EXIST_USER;

public interface UserJPARepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    void deleteByEmail(String email);

    default void existsUserByEmail(String email) {
        boolean isExistUser = existsByEmail(email);
        if(!isExistUser) {
            throw new ApiException(NOT_EXIST_USER);
        }
    }

    default User findUserByEmail(String email) {
        return findByEmail(email)
                .orElseThrow(() -> new ApiException(NOT_EXIST_USER));
    }
}
