package com.utec.pc1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, Long> {
    optional <Account> findByUsername(String u);
    optional <Account> findByEmail (String e);
    boolean existsByUsername(String u);
    boolean existsByEmail (String e);
}
