package com.vehicle.vms.repositoryImpl;

import com.vehicle.vms.dto.VehicleDto;
import com.vehicle.vms.model.DocumentsEntity;
import com.vehicle.vms.model.RoleEntity;
import com.vehicle.vms.model.UserEntity;
import com.vehicle.vms.model.VehicleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
@Repository
public class VehicleRepositoryImpl {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbc;

    public Integer createVehicleDetails(VehicleEntity insertVehicleDto) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "insert into vehicle(vehicle_name,vehicle_no,model,rc_no,chassis_no,engine_no,is_active,created_on,owner_id,created_by) " +
                "values(:vehicle_name,:vehicle_no,:model,:rc_no,:chassis_no,:engine_no,true,now(),:owner_id,:created_by);";
        sqlParam.addValue("vehicle_name", insertVehicleDto.getVehicleName());
        sqlParam.addValue("vehicle_no", insertVehicleDto.getVehicleNo());
        sqlParam.addValue("model", insertVehicleDto.getModel());
        sqlParam.addValue("rc_no", insertVehicleDto.getRcNo());
        sqlParam.addValue("chassis_no", insertVehicleDto.getChassisNo());
        sqlParam.addValue("engine_no", insertVehicleDto.getEngineNo());
//        sqlParam.addValue("driver_id", insertVehicleDto.getDriverId());
        sqlParam.addValue("owner_id", insertVehicleDto.getOwnerId());
        sqlParam.addValue("created_by", insertVehicleDto.getOwnerId());
        return namedJdbc.update(qry, sqlParam);

    }

    public Integer updateVehicleDetails(Integer id, String vehicleName, String vehicleNo, String engineNo, String model, String rcNo, String chassisNo,Integer updatedBy) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry="update vehicle set vehicle_name=:vehicle_name,vehicle_no=:vehicle_no,engine_no=:engine_no,model=:model,rc_no=:rc_no,chassis_no=:chassis_no, " +
                "  is_active=true,updated_on=now(),updated_by=:updated_by  where id=:id;";
        sqlParam.addValue("id",id);
        sqlParam.addValue("vehicle_name",vehicleName);
        sqlParam.addValue("vehicle_no",vehicleNo);
        sqlParam.addValue("engine_no",engineNo);
        sqlParam.addValue("model",model);
        sqlParam.addValue("rc_no",rcNo);
        sqlParam.addValue("chassis_no",chassisNo);
//        sqlParam.addValue("driver_id",driverId);
//        sqlParam.addValue("owner_id",ownerId);
        sqlParam.addValue("updated_by",updatedBy);
        return namedJdbc.update(qry,sqlParam);
    }

    public List<VehicleDto> getAllVehicleDetails() {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select v.id as id,v.vehicle_no as VehicleNo, v.vehicle_name as vehicleName,v.chassis_no as chassisNo,v.model as model,v.engine_no as engineNo,v.rc_no as rcNo,u.full_name as fullName from vehicle as v left join users as u" +
                " on v.driver_id=u.id ;";
        return namedJdbc.query(qry, sqlParam, new BeanPropertyRowMapper<>(VehicleDto.class));
    }

    public VehicleDto getVehicleDetails(Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry="select v.id as id,v.vehicle_no as VehicleNo, v.vehicle_name as vehicleName,v.chassis_no as chassisNo,v.model as model,v.engine_no as engineNo,v.rc_no as rcNo,u.full_name as fullName from vehicle as v left join users as u " +
                " on v.driver_id=u.id where v.id=:id";
        sqlParam.addValue("id",id);
        return namedJdbc.queryForObject(qry, sqlParam, new BeanPropertyRowMapper<>(VehicleDto.class));
    }

    public VehicleEntity deleteVehicleDetails(Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "delete from vehicle where id=:id;";
        sqlParam.addValue("id",id);
        return namedJdbc.queryForObject(qry, sqlParam,new BeanPropertyRowMapper<>(VehicleEntity.class));
    }

    public List<VehicleEntity> checkVehNo(String vehicleNo) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select vehicle_no from vehicle where vehicle_no=:vehicle_no AND is_active=true;";
        sqlParam.addValue("vehicle_no",vehicleNo);
        return namedJdbc.query(qry, sqlParam,new BeanPropertyRowMapper<>(VehicleEntity.class));
    }

    public List<VehicleEntity> checkVeh(String vehicleNo,Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "select vehicle_no from vehicle where vehicle_no=:vehicle_no AND is_active=true AND id!=:id;";
        sqlParam.addValue("vehicle_no",vehicleNo);
        sqlParam.addValue("id",id);
        return namedJdbc.query(qry, sqlParam,new BeanPropertyRowMapper<>(VehicleEntity.class));
    }

    public VehicleEntity mapVehicleDetails(Integer id, Integer driverId, Integer ownerId) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry="";
        if (driverId >= 0 && driverId != null) {
            qry = "update vehicle set driver_id=:driver_id where id=:id;";
            sqlParam.addValue("id", id);
            sqlParam.addValue("driver_id", driverId);
        }
        else if (ownerId >= 0 && ownerId != null) {
            qry = "update vehicle set owner_id=:owner_id where id=:id;";
            sqlParam.addValue("id", id);
            sqlParam.addValue("driver_id", driverId);
        }
        return namedJdbc.queryForObject(qry, sqlParam, new BeanPropertyRowMapper<>(VehicleEntity.class));
    }

    public Integer getDriverAssigned(Integer id,Integer driverId) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "update vehicle set driver_id=:driver_id where id=:id;";
        sqlParam.addValue("id", id);
        sqlParam.addValue("driver_id", driverId);
        return namedJdbc.update(qry,sqlParam);
    }

    public Integer getDriverUnassigned(Integer id) {
        MapSqlParameterSource sqlParam = new MapSqlParameterSource();
        String qry = "update vehicle set driver_id=null,updated_on=now() where id=:id;";
        sqlParam.addValue("id", id);
        return namedJdbc.update(qry,sqlParam);
    }

}
