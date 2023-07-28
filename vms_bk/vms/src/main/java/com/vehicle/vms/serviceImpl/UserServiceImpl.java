package com.vehicle.vms.serviceImpl;

import com.vehicle.vms.dto.DocumentsDto;
import com.vehicle.vms.dto.RoleDto;
import com.vehicle.vms.dto.UserDto;
import com.vehicle.vms.dto.VehicleDto;
import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.RoleEntity;
import com.vehicle.vms.model.UserEntity;
import com.vehicle.vms.model.VehicleEntity;
import com.vehicle.vms.repository.DocumentRepository;
import com.vehicle.vms.repository.RoleRepository;
import com.vehicle.vms.repository.UserRepository;
import com.vehicle.vms.repository.VehicleRepository;
import com.vehicle.vms.repositoryImpl.DocumentsRepositoryImpl;
import com.vehicle.vms.repositoryImpl.UserRepositoryImpl;
import com.vehicle.vms.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.*;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Value("${userImageFromFrontEnd}")
    private String folderName;

    Path roots;
    private String uriPan;
    private String uriAadhar;

    private String uriDl;


    private final String uploadDir = new ClassPathResource("C:/xampp/htdocs/VMS/uploads/").getPath();

    @Override
    public Integer createUserDetails(UserEntity insertUserDto) {
        return userRepositoryImpl.createUserDetails(insertUserDto);
    }

    @Override
    public List<UserDto> getAllUserDetails() {
        return userRepositoryImpl.getAllUserDetails();
    }

    @Override
    public UserDto getUserDetails(Integer id) {
        return userRepositoryImpl.getUserDetails(id);
    }

    @Override
    public DocumentsDto getImgByUserId(Integer id) {
        return userRepositoryImpl.getImgByUserId(id);
    }

    @Override
    public Integer updateUserDetails(Integer id, String fullName, String emailId,BigInteger phoneNo, String address, Integer updatedBy,Integer roleId) {
        return userRepositoryImpl.updateUserDetails(id, fullName, emailId,phoneNo,address, updatedBy,roleId);
    }

    @Override
    public UserEntity deleteUserDetails(Integer id) {
        return userRepositoryImpl.deleteUserDetails(id);
    }

    @Override
    public UserEntity emailIdCheck(String emailId) {

        return userRepositoryImpl.emailIdCheck(emailId);
    }

    @Override
    public UserEntity getUserData(Integer id) {

        return userRepositoryImpl.getUserData(id);
    }

    @Override
    public UserEntity saveRegisterUserDetails(UserDto insertUserDto, String encodedPw) {

        insertUserDto.setIsActive(true);
        insertUserDto.setPassword(encodedPw);
        insertUserDto.setRoleId((insertUserDto.getRoleId()));
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(insertUserDto, entity);
        return userRepository.save(entity);
    }

    @Override
    public List<UserEntity> email(String emailId) {
        return userRepositoryImpl.email(emailId);
    }



    @Override
    public DocumentsEntity registerDocumentsDetails(Integer id, MultipartFile panImage, MultipartFile aadharImage, MultipartFile dlImage) throws IOException {
        DocumentsEntity obj = new DocumentsEntity();
        String folder = folderName;
        obj.setIsActive(true);

        try {
            roots = Paths.get(uploadDir);

            if (panImage != null) {
                uriPan = panImage.getOriginalFilename();
                Files.copy(panImage.getInputStream(), roots.resolve(uriPan), StandardCopyOption.REPLACE_EXISTING);
                obj.setPanCard(id+"_"+panImage.getOriginalFilename());
            }
            if (aadharImage != null) {
                uriAadhar = aadharImage.getOriginalFilename();
                Files.copy(aadharImage.getInputStream(), roots.resolve(uriAadhar), StandardCopyOption.REPLACE_EXISTING);
                obj.setAadharNo(id+"_"+aadharImage.getOriginalFilename());
            }
            if (dlImage != null) {
                uriDl = dlImage.getOriginalFilename();
                Files.copy(dlImage.getInputStream(), roots.resolve(uriDl), StandardCopyOption.REPLACE_EXISTING);
                obj.setDl(id+"_" + dlImage.getOriginalFilename());
            }

            obj.setUserId(id);
            return documentRepository.save(obj);
        } catch (Exception e) {
            throw new RuntimeException("could not upload the image ");

        }
    }

    @Override
    public List<UserDto> getDriverDropdown()
    {
        return userRepositoryImpl.getDriverDropdown();
    }

    @Override
    public List<UserDto> getOwnerDropdown()
    {
        return userRepositoryImpl.getOwnerDropdown();
    }

}
