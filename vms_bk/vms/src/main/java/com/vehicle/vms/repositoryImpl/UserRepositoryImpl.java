package com.vehicle.vms.repositoryImpl;

import com.vehicle.vms.dto.DocumentsDto;
import com.vehicle.vms.dto.UserDto;
import com.vehicle.vms.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public class UserRepositoryImpl {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;


    public Integer createUserDetails(UserEntity insertUserDto) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "insert into users(full_name,email_id,phone_no,password,address,is_active,created_on,created_by) " +
                "values(:full_name,:email_id,:phone_no,:password,:address,true,now(),:created_by);";
        sqlParam.addValue("full_name", insertUserDto.getFullName());
        sqlParam.addValue("email_id", insertUserDto.getFullName());
        sqlParam.addValue("phone_no", insertUserDto.getPhoneNo());
        sqlParam.addValue("password", insertUserDto.getPassword());
        sqlParam.addValue("address", insertUserDto.getAddress());
        sqlParam.addValue("created_by", insertUserDto.getId());
//        sqlParam.addValue("updated_on", insertUserDto.getUpdatedOn());
//        sqlParam.addValue("updated_by", insertUserDto.getUpdatedBy());
        return namedJdbc.update(qry, sqlParam);
    }

    public List<UserDto> getAllUserDetails() {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select u.id,u.role_id,u.full_name,u.email_id,u.phone_no,u.address,u.password,u.is_active,u.created_on,u.created_by,u.updated_on,u.updated_by,r.role_name" +
                " from users u left join role r on u.role_id=r.id";
        return namedJdbc.query(qry, sqlParam, new BeanPropertyRowMapper<>(UserDto.class));
    }

    public UserDto getUserDetails(Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry="select u.id,u.full_name,u.email_id,u.address,u.phone_no,u.role_id,r.role_name from users u left join role r" +
                " on u.role_id=r.id where u.id=:id";
        sqlParam.addValue("id",id);
        return namedJdbc.queryForObject(qry, sqlParam, new BeanPropertyRowMapper<>(UserDto.class));
    }

    public Integer updateUserDetails(Integer id, String fullName, String emailId, BigInteger phoneNo,String address, Integer updatedBy,Integer roleId) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry="update users set full_name=:full_name,email_id=:email_id,phone_no=:phone_no,address=:address,"+
                " is_active=true,updated_on=now(),role_id=:role_id where id=:id;";
        sqlParam.addValue("id",id);
        sqlParam.addValue("full_name",fullName);
        sqlParam.addValue("email_id",emailId);
        sqlParam.addValue("phone_no",phoneNo);
        sqlParam.addValue("address",address);
        sqlParam.addValue("updated_by",updatedBy);
        sqlParam.addValue("role_id",roleId);
        return namedJdbc.update(qry,sqlParam);
    }

    public UserEntity deleteUserDetails(Integer id) {
            MapSqlParameterSource sqlParam = new MapSqlParameterSource();
            String qry = "delete from users where id=:id;";
            sqlParam.addValue("id",id);
            return namedJdbc.queryForObject(qry, sqlParam,new BeanPropertyRowMapper<>(UserEntity.class));

    }

    public UserEntity emailIdCheck(String emailId) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select * from users where email_id=:email_id ";
        sqlParam.addValue("email_id",emailId);
        return namedJdbc.queryForObject(qry, sqlParam, new BeanPropertyRowMapper<>(UserEntity.class));
    }
    public UserEntity getUserData(Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select id,role_id,full_name,email_id,phone_no,email_id,address,is_active,created_on,created_by,updated_on,updated_by " +
                "from users where id=:id;";
        sqlParam.addValue("id", id);
        return namedJdbc.queryForObject(qry, sqlParam, new BeanPropertyRowMapper<>(UserEntity.class));
    }

    public List<UserEntity> email(String emailId) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select email_id from users where email_id=:email_id;";
        sqlParam.addValue("email_id",emailId);
        return namedJdbc.query(qry, sqlParam, new BeanPropertyRowMapper<>(UserEntity.class));
    }

//    public Integer updateRoleName(Integer roleId,String roleName) {
//        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
//        String qry = "update role set roleName=:roleName where id=:id";
//        sqlParam.addValue("id",roleId);
//        sqlParam.addValue("roleName",roleName);
//        return namedJdbc.update(qry,sqlParam);
//    }

    public DocumentsDto getImgByUserId(Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select pan_card as panCard,aadhar_no as aadharNo,dl from documents where user_id=:id;";
        sqlParam.addValue("id",id);
        try{
            return namedJdbc.queryForObject(qry,sqlParam,new BeanPropertyRowMapper<>(DocumentsDto.class));
        } catch (Exception e){
            return null;
        }
    }

    public List<UserDto> getDriverDropdown() {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select u.id,u.full_name from users u inner join role r on u.role_id=r.id where u.is_active='true' AND r.id=4;";
        return namedJdbc.query(qry,sqlParam,new BeanPropertyRowMapper<>(UserDto.class));
    }

    public List<UserDto> getOwnerDropdown() {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select u.id,u.full_name from users u inner join role r on u.role_id=r.id where u.is_active='true' AND r.id=2;";
        return namedJdbc.query(qry,sqlParam,new BeanPropertyRowMapper<>(UserDto.class));
    }
}
