package com.bootcamp.schedulemanagementjpaapp.repository;

import com.bootcamp.schedulemanagementjpaapp.entity.Manage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManageJPARepository extends JpaRepository<Manage, Long> {
}