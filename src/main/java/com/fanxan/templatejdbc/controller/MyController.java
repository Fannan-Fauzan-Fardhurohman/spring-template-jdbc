package com.fanxan.templatejdbc.controller;

import com.fanxan.templatejdbc.dao.MysqlDao;
import com.fanxan.templatejdbc.model.DataModel;
import com.fanxan.templatejdbc.model.LoginModel;
import com.fanxan.templatejdbc.model.MessageModel;
import com.fanxan.templatejdbc.model.ModelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.Message;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@CrossOrigin(origins = "*")
@RestController
@Primary
public class MyController {
    @Autowired
    MysqlDao mysqlDao;

    MessageModel messageModel = new MessageModel();

    @RequestMapping(value = "/all")
    public @ResponseBody
    MessageModel getAll() {
        try {
            messageModel = mysqlDao.getAllData();
        } catch (Exception e) {

        }
        return messageModel;
    }

    @GetMapping("/alls")
    @ResponseBody
    public ResponseEntity<?> getALL() {
        messageModel = mysqlDao.getAllData();
        return new ResponseEntity(messageModel, HttpStatus.OK);
    }

    @RequestMapping(value = "/findBy")
    public @ResponseBody
    MessageModel findBy(@RequestParam("field") String field, @RequestParam("value") String value) {
        try {
            messageModel = mysqlDao.findBy(field, value);
        } catch (Exception e) {

        }
        return messageModel;
    }

    @PostMapping(value = "/insert")
    public @ResponseBody
    MessageModel insert(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("city") String city, @RequestParam("age") int age, @RequestParam("job") String job) {
        DataModel dataModel = new DataModel(name, email, city, age, job);
        try {
            messageModel = mysqlDao.insert(dataModel);
        } catch (Exception e) {

        }
        return messageModel;
    }

    @PostMapping(value="/signup")
    public @ResponseBody MessageModel signup(@RequestParam("id") int id, @RequestParam("username") String username, @RequestParam("password") String password){
        String pasMd5 = getMd5(password);
        LoginModel dataModel = new LoginModel(id,username,pasMd5);
        try{
            messageModel = mysqlDao.register(dataModel);
        }catch (Exception e){

        }
        return messageModel;
    }

    @RequestMapping(value = "/deletes")
    public @ResponseBody
    MessageModel deletes(@RequestParam("id") int id) {
        try {
            DataModel dataModel = new DataModel(id);
            messageModel = mysqlDao.deletes(dataModel);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageModel;
    }

    @RequestMapping(value = "/update")
    public @ResponseBody
    MessageModel updates(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("city") String city, @RequestParam("age") int age, @RequestParam("job") String job) {
        try {
            DataModel dataModel = new DataModel(id, name, email, city, age, job);
            messageModel = mysqlDao.update(dataModel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messageModel;
    }

    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
