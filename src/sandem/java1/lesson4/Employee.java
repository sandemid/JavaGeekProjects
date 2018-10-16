package sandem.java1.lesson4;

/**
 * Класс сотрудников. Задания 1-3
 */

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Employee(String _fullName, String _position, String _email, String _phone, int _salary, int _age) {
        this.fullName = _fullName;
        this.position = _position;
        this.email = _email;
        this.phone = _phone;
        this.salary = (_salary <= 0) ? 0 : _salary;
        this.age = (_age < 18 || _age > 100) ? 0 : _age;
    }

    public Employee() { }

    public void getInfo() {
        System.out.println("Name - " + fullName + "; position - " + position + "; e-mail - " + email + "; phone number - " + phone + "; salary - " + salary + "; age - " + age);
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position;
    }
}
