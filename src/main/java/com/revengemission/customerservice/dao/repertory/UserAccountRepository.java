package com.revengemission.customerservice.dao.repertory;

import com.revengemission.customerservice.dao.entity.UserAccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {
    UserAccountEntity findByUsername(String username);

    Page<UserAccountEntity> listByRole(String role, Pageable pageable);
}
