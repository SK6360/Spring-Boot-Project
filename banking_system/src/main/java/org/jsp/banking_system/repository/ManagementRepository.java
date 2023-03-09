package org.jsp.banking_system.repository;

import org.jsp.banking_system.dto.Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagementRepository extends JpaRepository<Management, Integer> {

}
