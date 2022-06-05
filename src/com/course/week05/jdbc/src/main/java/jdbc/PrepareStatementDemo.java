package jdbc;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;

/**
 * Demo using PrepareStatement
 *
 * @author bruce.zhu@GeekTrainingCamp
 * @since 2022-06-05 08:35:44
 */
public class PrepareStatementDemo {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final Connection connection = getConnection();

            final String query = "SELECT id, name, code, email, birthday, height, create_time FROM test_student where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            doQuery(preparedStatement, 1);
            doQuery(preparedStatement, 2);
        } catch (Exception e) {
            e.printStackTrace();
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
        rs.close();
    }

    private static Connection getConnection() throws SQLException {
        final String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&useSSL=false&characterEncoding=utf-8";
        final String username = "dev";
        final String password = "dev";
        return DriverManager.getConnection(url, username, password);
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
