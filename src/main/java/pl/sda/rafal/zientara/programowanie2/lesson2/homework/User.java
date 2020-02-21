package pl.sda.rafal.zientara.programowanie2.lesson2.homework;

import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.FileReader;
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

    public void numberOfEmployees() {
        System.out.println(getName() + ", liczba pracowników wynosi: " + employeeList.size());
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void getAllEmployees() {
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

    public void downloadFile(User user) {
        try {
            FileReader fr = new FileReader("baza.csv");
            BufferedReader br = new BufferedReader(fr);
            StringBuilder lineBuilder = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                lineBuilder.append(line);
                String[] elements = line.split(";");
                user.addEmployee(new Employee(elements[0], elements[1], Integer.parseInt(elements[2])));
                lineBuilder.append(System.lineSeparator());
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Nie znaleziono takiego pliku!");
        }
    }



}
