package com.pys.java;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.pys.java.util.CommUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;


import java.sql.*;
import java.util.Properties;

public class JDBCTest {
    private static DruidDataSource dataSource;
    static {
        Properties props = CommUtils.loadProperties("datasource.properties");
        try {
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*查询操作*/
    @Test
    public void testQuery(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
            connection = (Connection) dataSource.getPooledConnection();
            String sql = "SELECT * from user";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            /*判断有没有返回值*/
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String userName = resultSet.getString("username");
                String password = resultSet.getString("password");
                String brief = resultSet.getString("brief");
                System.out.println("id为："+id+",username为："+userName+",password为："+password+",brief为："+brief);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResources(connection,statement,resultSet);
        }
    }

    /*插入测试*/
@Test
    public void testInsert(){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = (Connection) dataSource.getPooledConnection();
            String password = DigestUtils.md5Hex("123");
            String sql = "insert into user(username,password,brief)"+"value (?,?,?)";
            statement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,"test1");
            statement.setString(2,password);
            statement.setString(3,"你真帅");
            /*受影响的行数*/
            int rows = statement.executeUpdate();
            Assert.assertEquals(1,rows);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeResources(connection,statement);
        }
}

/*测试登陆*/
    @Test
    public void testLogin(){
        String userName = "test";
        String password = "123";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = (Connection) dataSource.getPooledConnection();
            String sql = "select * from user where username = '"+userName+"'"+"and password = '"+password+"'";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                System.out.println("登陆成功");
            }else{
                System.out.println("登陆失败");
            }
        }catch (SQLException e){

        }finally {
            closeResources(connection,statement,resultSet);
        }
    }


/*关闭资源方法 1*/
    public void closeResources(Connection connection, Statement statement){
        if(connection != null){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(statement != null){
            try{
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
/*关闭资源方法 2 */
    public void closeResources(Connection connection,
                               Statement statement,
                               ResultSet resultSet){
        closeResources(connection,statement);
        if(resultSet != null){
            try{
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
