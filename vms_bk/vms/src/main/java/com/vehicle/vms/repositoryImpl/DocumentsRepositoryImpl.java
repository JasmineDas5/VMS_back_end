package com.vehicle.vms.repositoryImpl;

import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DocumentsRepositoryImpl {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;

    public Integer createDetails(DocumentsEntity insertDetailsDto) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry ="insert into documents(user_id,pan_card,aadhar_card,dl,is_active,created_on,created_by) " +
                "values(:user_id,:pan_card,:aadhar_card,true,now(),:created_by);";
//        sqlParam.addValue("id",insertDetailsDto.getId());
        sqlParam.addValue("user_id",insertDetailsDto.getUserId());
        sqlParam.addValue("pan_card",insertDetailsDto.getPanCard());
        sqlParam.addValue("aadhar_card",insertDetailsDto.getAadharNo());
        sqlParam.addValue("dl",insertDetailsDto.getDl());
        sqlParam.addValue("created_by",insertDetailsDto.getUserId());
        return namedJdbc.update(qry, sqlParam);
    }

    public List<DocumentsEntity> userIdDetails(Integer userId) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry="select user_id from documents where user_id=:userId;";
        sqlParam.addValue("userId",userId);
        return namedJdbc.query(qry, sqlParam, new BeanPropertyRowMapper<>(DocumentsEntity.class));
    }


    public Integer updateDocDetails(Integer id,Integer userId,String panCard,String aadharCard,String dl,Integer updatedBy) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry="update documents set user_id=:user_id,pan_card=:pan_card,aadhar_card=:aadhar_card," +
                " dl=:dl,is_active=true,updated_on=now(),updated_by=:updated_by  where id=:id;";
        sqlParam.addValue("id",id);
        sqlParam.addValue("user_id",userId);
        sqlParam.addValue("pan_card",panCard);
        sqlParam.addValue("aadhar_card",aadharCard);
        sqlParam.addValue("dl",dl);
        sqlParam.addValue("updated_by",updatedBy);
        return namedJdbc.update(qry,sqlParam);
    }
    public List<DocumentsEntity> getAllDetails() {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select * from documents";
        return namedJdbc.query(qry, sqlParam, new BeanPropertyRowMapper<>(DocumentsEntity.class));
    }

    public DocumentsEntity getDetails(Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry="select * from documents where id=:id;";
        sqlParam.addValue("id",id);
        return namedJdbc.queryForObject(qry, sqlParam, new BeanPropertyRowMapper<>(DocumentsEntity.class));
    }

    public DocumentsEntity deleteDocDetails(Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "delete from documents where id=:id;";
        sqlParam.addValue("id",id);
        return namedJdbc.queryForObject(qry, sqlParam,new BeanPropertyRowMapper<>(DocumentsEntity.class));
    }

}
