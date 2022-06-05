package jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Demo using Hikari
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-05 10:15:03
 */
public class HikariDemo {
    public static void main(String[] args) {
        HikariDataSource dataSource = null;
        try {
            dataSource = new HikariDataSource(hikariConfig());
            final Connection connection = dataSource.getConnection();

            final String query = "SELECT id, name, code, email, birthday, height, create_time FROM test_student where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            doQuery(preparedStatement, 1);
            doQuery(preparedStatement, 2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dataSource != null) {
                dataSource.close();
            }
        }
    }

    private static void doQuery(final PreparedStatement preparedStatement, final long id) throws SQLException {
        preparedStatement.setLong(1, id);
        final ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            final TestStudent student = new TestStudent();
            student.id = rs.getLong("id");
            student.name = rs.getString("name");
            student.code = rs.getString("code");
            student.email = rs.getString("email");
            student.birthday = rs.getTimestamp("birthday");
            student.height = rs.getBigDecimal("height");
            student.createTime = rs.getTimestamp("create_time");
            System.out.println("TestStudent : " + student);
        }
    }

    private static HikariConfig hikariConfig() {
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://10.0.45.179:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf-8");
        config.setUsername("rta-sit");
        config.setPassword("snowball");
        config.setPoolName("testPool");
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(10);
        config.setIdleTimeout(10 * 6000);
        return config;
    }

    static class TestStudent {
        private Long id;
        private String name;
        private String code;
        private String email;
        private java.util.Date birthday;
        private BigDecimal height;
        private Date createTime;

        @Override
        public String toString() {
            return "TestStudent{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    ", email='" + email + '\'' +
                    ", birthday=" + birthday +
                    ", height=" + height +
                    ", createTime=" + createTime +
                    '}';
        }
    }
}
