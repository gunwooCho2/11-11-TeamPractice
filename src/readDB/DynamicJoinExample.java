package readDB;

import java.sql.*;
import java.util.*;

public class DynamicJoinExample {

    // 데이터베이스 연결 (Oracle 예시)
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
    }

    // 동적으로 SQL JOIN 쿼리 생성
    public static String createJoinQuery(Map<String, String> joinConditions) {
        // 기본 SELECT 쿼리
        StringBuilder query = new StringBuilder("SELECT e.employee_name, d.department_name, s.salary, p.project_name, t.task_name FROM employees e ");

        // 동적으로 JOIN을 추가
        if (joinConditions.containsKey("join1")) {
            query.append("INNER JOIN departments d ON e.department_id = d.department_id ");
        }
        if (joinConditions.containsKey("join2")) {
            query.append("INNER JOIN salaries s ON e.employee_id = s.employee_id ");
        }
        if (joinConditions.containsKey("join3")) {
            query.append("INNER JOIN projects p ON e.employee_id = p.employee_id ");
        }
        if (joinConditions.containsKey("join4")) {
            query.append("INNER JOIN tasks t ON p.project_id = t.project_id ");
        }

        // WHERE 조건 (예시로 employee_id가 100 이상인 경우로 설정)
        query.append("WHERE e.employee_id > 100;");
        
        return query.toString();
    }

    public static void executeQuery() {
        try (Connection conn = getConnection()) {
            // join1, join2, join3, join4 키를 사용하여 동적 쿼리 생성
            Map<String, String> joinConditions = new HashMap<>();
            joinConditions.put("join1", "departments");  // join1을 사용하여 departments 테이블을 JOIN
            joinConditions.put("join2", "salaries");     // join2를 사용하여 salaries 테이블을 JOIN
            joinConditions.put("join3", "projects");     // join3을 사용하여 projects 테이블을 JOIN
            joinConditions.put("join4", "tasks");        // join4을 사용하여 tasks 테이블을 JOIN

            String query = createJoinQuery(joinConditions);
            System.out.println("Generated SQL Query: " + query);  // 생성된 SQL 출력

            // 쿼리 실행
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                // 결과 출력
                while (rs.next()) {
                    String employeeName = rs.getString("employee_name");
                    String departmentName = rs.getString("department_name");
                    int salary = rs.getInt("salary");
                    String projectName = rs.getString("project_name");
                    String taskName = rs.getString("task_name");

                    System.out.println("Employee: " + employeeName + ", Department: " + departmentName + ", Salary: " + salary
                            + ", Project: " + projectName + ", Task: " + taskName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 쿼리 실행
        executeQuery();
    }
}

