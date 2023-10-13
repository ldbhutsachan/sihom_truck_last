package com.ldb.truck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.DriverManager;
import java.sql.Connection;
@SpringBootApplication
public class TruckApplication {
	public static void main(String[] args) {
// 		Connection connection = null;
// //		spring.ebank.url=jdbc:mysql://localhost:3306/your_database_name
// //		spring.ebank.username=sihomdb
// //		spring.ebank.password=ojsGnsIL48bV2wEF
// 		String url = "jdbc:mariadb://178.128.214.105:33066/khounkham_logistics_db_EABh1xCQuo";
// 		String user = "khounkham_logistics_user";
// 		String pwd = "eBe12345678$DB";
// 		try{
// 			connection = DriverManager.getConnection(url,user,pwd);
// 		}catch (Exception e){e.printStackTrace();}

// 		System.out.println("done connection");

		SpringApplication.run(TruckApplication.class, args);
	}

}
