package com.fanxan.templatejdbc.dao.impl;

import com.fanxan.templatejdbc.dao.MysqlDao;
import com.fanxan.templatejdbc.model.DataModel;
import com.fanxan.templatejdbc.model.LoginModel;
import com.fanxan.templatejdbc.model.MessageModel;
import com.fanxan.templatejdbc.model.ModelId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class MysqlDaoImpl implements MysqlDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlDaoImpl.class);
    Date date = new Date();
    String theDate = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(date);
    String insertData = "insert into personal values(?,?,?,?,?,?)";
    String insertLogin = "insert into login values(?,?,?)";

    //    INSERT INTO `personal` (`id`, `name`, `email`, `city`, `age`, `job`) VALUES (NULL, 'fannan', 'fannan@smkn4padalarang.sch.id', 'bandung', '18', 'Programmer');
    MessageModel msg = new MessageModel();
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public MessageModel getAllData() {
        String sql = "select * from personal";
        try {
            List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
            msg.setData(data);
            msg.setStatus("true");
        } catch (Exception e) {
            msg.setData("null");
            msg.setStatus("false");
            msg.setMessage(e.getMessage());
        }
        LOGGER.info(sql + "has been executed - " + theDate);
        return msg;
    }

    @Override
    public MessageModel findBy(String field, String value) {
        String sql = "select * from personal where " + field + "='" + value + "'";
        try {
            List<Map<String, Object>> data = jdbcTemplate.queryForList(sql);
            msg.setData(data);
            msg.setStatus("true");
        } catch (Exception e) {
            msg.setData("null");
            msg.setStatus("false");
            msg.setMessage(e.getMessage());
        }
        LOGGER.info(sql + "has been executed - " + theDate);
        return msg;
    }

    @Override
    public MessageModel insert(DataModel data) {
        try {
            jdbcTemplate.update(insertData, new Object[]{
                    data.getId(), data.getName(), data.getEmail(), data.getCity(), data.getAge(), data.getJob()
            });
            msg.setData(data.getName() + " has been saved !");
            msg.setStatus("200");
            msg.setMessage("True");
        } catch (Exception e) {
            msg.setData(data.getId() + " Failed insert");
            msg.setStatus("404");
            msg.setMessage(e.getMessage());
        }
        LOGGER.info(insertData + "has been executed - " + theDate);
        return msg;
    }
    @Override
    public MessageModel register(LoginModel data) {
        try{
            jdbcTemplate.update(insertLogin,new Object[]{
               data.getId(),data.getUsername(),data.getPassword()
            });
            msg.setData("user "+data.getUsername() + " has been created !");
            msg.setStatus("200");
            msg.setMessage("True");
        }catch (Exception e){
            msg.setData("user "+data.getUsername() + " failed created");
            msg.setStatus("404");
            msg.setMessage(e.getMessage());
        }
        LOGGER.info(insertLogin + "has been executed - "+theDate);
        return msg;
    }

    @Override
    public MessageModel deletes(DataModel dataModel) {
        String sql = "delete from personal where id='" + dataModel.getId() + "'";
        try {
            jdbcTemplate.update(sql);
            msg.setData("user with id " + dataModel.getId() + " has been deleted !");
            msg.setStatus("200");
            msg.setMessage("True");
        } catch (Exception e) {
            msg.setData("null");
            msg.setStatus("false");
            msg.setMessage(e.getMessage());
        }
        LOGGER.info(sql + "has been executed - " + theDate);
        return msg;
    }

    @Override
    public MessageModel update(DataModel dataModel) {
        String sql = "update personal set name ='" + dataModel.getName() + "',email='" + dataModel.getEmail() + "',city='" + dataModel.getCity() + "',age='" + dataModel.getAge() + "',job='" + dataModel.getJob() + "' where id ='" + dataModel.getId() + "'";
        try{
            jdbcTemplate.update(sql);
            msg.setData("user with id " + dataModel.getId() + " has been update !");
            msg.setStatus("200");
            msg.setMessage("True");
        }catch (Exception e){
            msg.setData("null");
            msg.setStatus("false");
            msg.setMessage(e.getMessage());
        }
        LOGGER.info(sql + "has been executed - "+theDate);
        return msg;
    }

}
