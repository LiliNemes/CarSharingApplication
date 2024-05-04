package org.example.carsharing_server.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
