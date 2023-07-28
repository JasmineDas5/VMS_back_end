package com.vehicle.vms.repository;

import com.vehicle.vms.model.DocumentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentsEntity,Integer> {
}
