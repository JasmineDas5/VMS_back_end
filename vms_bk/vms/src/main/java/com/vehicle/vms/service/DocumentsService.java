package com.vehicle.vms.service;

import com.vehicle.vms.model.DocumentsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface DocumentsService {
    List<DocumentsEntity> userIdDetails(Integer userId);

    Integer createDetails(DocumentsEntity insertDetailsDto);

    Integer updateDocDetails(Integer id, Integer userId, String panCard, String aadharCard, String dl,Integer updatedBy);

    List<DocumentsEntity> getAllDetails();

    DocumentsEntity getDetails(Integer id);

    DocumentsEntity deleteDocDetails(Integer id);
}
