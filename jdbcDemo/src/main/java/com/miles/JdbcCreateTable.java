package com.miles;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Properties;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JdbcCreateTable {

		  private static String jdbc_driver;  
		  private static String db_url;
		  private static String user;
		  private static String pass;
		  private static Properties properties = new Properties();
		  private static String id;
		  static {
			  // 使用ClassLoader加载properties配置文件生成对应的输入流
			  InputStream in = JdbcCreateTable.class.getClassLoader().getResourceAsStream("config.properties");
			  // 使用properties对象加载输入流
			  try {
				  	InputStreamReader isr = new InputStreamReader(in,"UTF-8");
				  	properties.load(isr);
				  	//获取key对应的value值
				  	jdbc_driver = properties.getProperty("jdbc_driver");
				  	db_url = properties.getProperty("db_url");
				  	user = properties.getProperty("user");
				  	pass = properties.getProperty("password");
				  	id = properties.getProperty("id");
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
		  @Test
		  public void test() {
			   //需要关闭的资源放在try后面的括号中，可以自己关闭 ，不用我们写finally语句来关闭了！
			   try(Connection conn  = DriverManager.getConnection(db_url, user, pass);
				   Statement stmt =  conn.createStatement();){
				   	Class.forName(jdbc_driver);
					String json = properties.getProperty("json");
					StringBuilder createTable = new StringBuilder();
					String tableName = properties.getProperty("table_name");
					createTable.append("CREATE TABLE ").append(tableName).append(" ( \n\t"+id+" int(32) primary key auto_increment comment '自增主键', \n\t");
					JSONObject jsonObj = JSONObject.parseObject(json);
					JSONObject dataObj = (JSONObject)jsonObj.get("data");
					if(null != dataObj) {
						JSONArray fieldObjs = (JSONArray) dataObj.get("serviceField");
						if(null != fieldObjs) {
							Iterator<Object> iterator = fieldObjs.iterator();
							while(iterator.hasNext()) {
								JSONObject fieldObj = (JSONObject) iterator.next();
								String fieldName = fieldObj.getString("fieldName");
								String remark = fieldObj.getString("remark");
								String fieldType = fieldObj.getString("fieldType");
								if(null != fieldName && null != fieldType) {
									if(remark != null && !"".equals(remark.trim())) {
										createTable.append(fieldName+" ");
										if("string".equals(fieldType)) {
											createTable.append("varchar(255) ");
										}else if("date".equals(fieldType)) {
											createTable.append("datetime ");
										}else if("double".equals(fieldType)) {
											createTable.append("double ");
										}
										createTable.append("comment '"+remark+"',\n\t");
									}else {
										createTable.append(fieldName+" ");
										if("string".equals(fieldType)) {
											createTable.append("varchar(255),\n\t");
										}else if("date".equals(fieldType)) {
											createTable.append("datetime,\n\t");
										}else if("double".equals(fieldType)) {
											createTable.append("double,\n\t");//\n\t算两个字符
										}
									}
								}
							}
							createTable.delete(createTable.length()-3, createTable.length()).append("\n)");
							System.out.println(createTable);
							stmt.executeUpdate(createTable.toString());
						}
					}
			   }catch(SQLException se){
			      se.printStackTrace();
			   }catch(Exception e){
			      e.printStackTrace();
			   }
			   System.out.println("Goodbye!");
		}//end main
}
