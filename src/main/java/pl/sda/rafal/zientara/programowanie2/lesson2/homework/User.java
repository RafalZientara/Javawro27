package pl.sda.rafal.zientara.programowanie2.lesson2.homework;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class User {
    private String name;
    private List<Employee> employeeList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void howManyEmployeesWeHave() {
        System.out.println(getName() + ", liczba pracowników wynosi: " + employeeList.size());
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void getDataOfAllEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee.toString());
        }
    }

    public void sortEmployeesBySurname() {
        employeeList.sort(Comparator.comparing(Employee::getSurname));
    }

    public void sortEmployees(int choice) {
        if (choice == 0) {
            employeeList.sort(Comparator.comparing(Employee::getName));
        } else if (choice == 1) {
            employeeList.sort(Comparator.comparing(Employee::getSurname));
        } else if (choice == 2) {
            employeeList.sort(Comparator.comparing(Employee::getAge));
        }
    }

    public void writeToCSV(String csvFileName) {
        StringBuilder builder = new StringBuilder();
        for (Employee employee : employeeList) {
            builder
                    .append(employee.getName().toUpperCase())
                    .append(employee.getName().equals("") ? "" : ";")
                    .append(employee.getSurname().toUpperCase())
                    .append(employee.getSurname().equals("") ? "" : ";")
                    .append(employee.getAge())
                    .append(";")
                    .append("\n");
        }
        try {
            FileWriter writer = new FileWriter(csvFileName);
            writer.write(String.valueOf(builder));
            writer.close();
        } catch (IOException e) {
            System.out.println("Niestety nie mogę utworzyć pliku");
        }

    }

}
