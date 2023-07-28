package com.vehicle.vms.serviceImpl;

import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.VehicleEntity;
import com.vehicle.vms.repository.VehicleRepository;
import com.vehicle.vms.repositoryImpl.DocumentsRepositoryImpl;
import com.vehicle.vms.repositoryImpl.VehicleRepositoryImpl;
import com.vehicle.vms.service.DocumentsService;
import com.vehicle.vms.service.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DocumentsServiceImpl implements DocumentsService {

    @Autowired
    private DocumentsRepositoryImpl documentsRepositoryImpl;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Integer createDetails(DocumentsEntity insertDetailsDto) {
        return documentsRepositoryImpl.createDetails(insertDetailsDto);
    }

    @Override
    public List<DocumentsEntity> userIdDetails(Integer userId) {
        return documentsRepositoryImpl.userIdDetails(userId);
    }

    @Override
    public Integer updateDocDetails(Integer id,Integer userId,String panCard,String aadharCard,String dl,Integer updatedBy) {
        return documentsRepositoryImpl.updateDocDetails(id,userId,panCard,aadharCard,dl,updatedBy);
    }

    @Override
    public List<DocumentsEntity> getAllDetails() {
        return documentsRepositoryImpl.getAllDetails();
    }

    @Override
    public DocumentsEntity getDetails(Integer id) {

        return documentsRepositoryImpl.getDetails(id);
    }

    @Override
    public DocumentsEntity deleteDocDetails(Integer id) {
        return documentsRepositoryImpl.deleteDocDetails(id);
    }

}
