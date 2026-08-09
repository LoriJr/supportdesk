package com.viratech.supportdesk.repository;

import com.viratech.supportdesk.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
