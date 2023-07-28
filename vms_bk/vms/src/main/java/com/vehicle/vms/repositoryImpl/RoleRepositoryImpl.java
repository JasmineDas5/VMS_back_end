package com.vehicle.vms.repositoryImpl;

import com.vehicle.vms.model.RoleEntity;
import com.vehicle.vms.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepositoryImpl {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;

    public Integer createRoleDetails(RoleEntity inserRoleDto) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "insert into role(role_name,is_active,created_on,created_by) " +
                "values(:role_name,true,now(),:created_by);";
        sqlParam.addValue("role_name", inserRoleDto.getRoleName());
        sqlParam.addValue("created_by", inserRoleDto.getCreatedBy());
        return namedJdbc.update(qry, sqlParam);
    }

    public List<RoleEntity> getAllRoleDetails() {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select * from role";
        return namedJdbc.query(qry, sqlParam, new BeanPropertyRowMapper<>(RoleEntity.class));
    }

    public RoleEntity getRoleDetails(Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry="select * from role where id=:id;";
        sqlParam.addValue("id",id);
        return namedJdbc.queryForObject(qry, sqlParam, new BeanPropertyRowMapper<>(RoleEntity.class));
    }

    public Integer updateRoleDetails(Integer id, String roleName,Integer updatedBy) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry="update role set role_name=:roleName,is_active=true,updated_on=now(),updated_by=:updated_by  where id=:id;";
        sqlParam.addValue("id",id);
        sqlParam.addValue("role_name",roleName);
        sqlParam.addValue("updated_by",updatedBy);
        return namedJdbc.update(qry,sqlParam);
    }


    public RoleEntity deleteRoleDetails(Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "delete from role where id=:id;";
        sqlParam.addValue("id",id);
        return namedJdbc.queryForObject(qry, sqlParam,new BeanPropertyRowMapper<>(RoleEntity.class));
    }

    public List<RoleEntity> getRoleDropdown() {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select id,role_name from role where is_active='true' order by id;";
        return namedJdbc.query(qry, sqlParam, new BeanPropertyRowMapper<>(RoleEntity.class));
    }
}
