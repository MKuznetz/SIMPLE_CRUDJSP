package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
	private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
            	Properties prop = new Properties();
                //тут нада указывать полный путь до файла
            	InputStream inputStream = new FileInputStream("C:/mytest/src/main/resources/db.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                System.out.println("Ошибка в DbUtil.java: ClassNotFoundException");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Ошибка в DbUtil.java: SQLException");
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                System.out.println("Ошибка в DbUtil.java: FileNotFoundException db.properties не найден");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Ошибка в DbUtil.java: IOException");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Ошибка в DbUtil.java: NullPointerException вместо db.properties");
                e.printStackTrace();
            }
            return connection;
        }

    }
}
