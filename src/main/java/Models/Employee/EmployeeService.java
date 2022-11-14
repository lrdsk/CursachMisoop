package Models.Employee;

import Database.MyDateBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EmployeeService {
    private MyDateBase dateBase = null;

    public EmployeeService(MyDateBase dateBase) {
        this.dateBase = dateBase;
    }

    public void setInfoAboutEmployeeToDB(Employee employee) throws SQLException {
        Statement statement = dateBase.getConnection().createStatement();
        String SQL = String.format("select exists (Select * from employee where employee_id = %d)", employee.getId());

        ResultSet resultSet = statement.executeQuery(SQL);

        resultSet.next();
        if (!resultSet.getBoolean("exists")) {
            SQL = String.format("INSERT INTO employee \nvalues (%d,'%s', %d)", employee.getId(), employee.getNameEmployee(),
                    employee.getSalaryEmployee());
            statement.executeUpdate(SQL);
        } else {
            System.out.println("Неверно, данный id уже занят в таблице.");
        }
    }

    private Employee mapEmployee(ResultSet result) throws SQLException {
        int employee_id = result.getInt("employee_id");
        String employee_name = result.getString("employee_name");
        int salary = result.getInt("salary");
        return new Employee(employee_id, employee_name, salary);
    }

    public Employee getInfoAboutEmployeeFromDB(String name) throws SQLException {
        PreparedStatement statement = dateBase.getConnection().prepareStatement(
                "SELECT * FROM employee where employee_name = ? LIMIT 1"
        );
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        result.next();
        return this.mapEmployee(result);
    }

    public Collection<Employee> getAllEmployeesByName(String name) throws SQLException {
        PreparedStatement statement = dateBase.getConnection().prepareStatement(
                "SELECT * FROM employee where employee_name = ?"
        );
        statement.setString(1, name);
        ResultSet result = statement.executeQuery();

        List<Employee> resultList = new ArrayList<>();
        while (result.next()) {
            resultList.add(this.mapEmployee(result));
        }

        return resultList;
    }
}
