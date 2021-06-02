package com.fanxan.templatejdbc.dao;

import com.fanxan.templatejdbc.model.DataModel;
import com.fanxan.templatejdbc.model.LoginModel;
import com.fanxan.templatejdbc.model.MessageModel;
import com.fanxan.templatejdbc.model.ModelId;

public interface MysqlDao {
    MessageModel getAllData();
    MessageModel findBy(String field, String value);
    MessageModel insert(DataModel data);
    MessageModel deletes(DataModel dataModel);
    MessageModel update(DataModel dataModel);
    MessageModel register(LoginModel data);
}
