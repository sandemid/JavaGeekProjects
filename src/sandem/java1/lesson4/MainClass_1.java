package sandem.java_1.lesson4_1;

/**
 * Главный класс. Задание 4
 */

public class MainClass_1 {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Alex","manager","alex@company.ru","+7-951-456-85-14",55000,35);
        employees[1] = new Employee("Peter","CEO","peter@company.ru","+7-951-231-52-74",250000,45);
        employees[2] = new Employee("Jane","accountant","jane@company.ru","+7-951-120-96-74",45000,25);
        employees[3] = new Employee("Liza","junior manager","liza@company.ru","+7-951-143-36-52",35000,21);
        employees[4] = new Employee("Max","manager","max@company.ru","+7-951-782-63-11",50000,41);
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() > 40) {
                employees[i].getInfo();
            }
        }
    }
}