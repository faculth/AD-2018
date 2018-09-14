package db;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DbConnection {

        private final DataSource dataSource;
        private Connection connection;


        public DbConnection() throws Exception {
            this.dataSource = getDataSource();
            this.connection = dataSource.getConnection();
        }


        public void close() {
            try {
                connection.close();
            } catch (Exception e) {
                System.out.println("Mensaje Error: " + e.getMessage());
            }
        }


        public ResultSet getResults(String query)  throws Exception {
            ResultSet result_set = null;
            Statement statement = getAStatement();
            return statement.executeQuery(query);
        }

        public int  execute(String query) throws Exception {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if(keys.next()) {
                int key = keys.getInt(1);
                return key;
            }
            return 0;
        }

        private Statement getAStatement() throws Exception {
            return connection.createStatement();
        }

        private DataSource getDataSource() throws Exception {
            MysqlDataSource mysqlDS = new MysqlDataSource();
            mysqlDS.setURL("jdbc:mysql://localhost:3306/demo?serverTimezone=ART");
            mysqlDS.setUser("root");
            mysqlDS.setPassword("root");
            return mysqlDS;
        }
}

