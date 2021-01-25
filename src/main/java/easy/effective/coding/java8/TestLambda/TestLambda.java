package easy.effective.coding.java8.TestLambda;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestLambda {

    static List<Employee> employees = Arrays.asList(
      new Employee("zhangsan",21, 1000),
      new Employee("lisi",22,2000),
      new Employee("wangwu",23,3000)
    );

    static List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp){
        List<Employee> emps = new ArrayList<>();

        for (Employee e : list) {
            if(mp.test(e)){
                emps.add(e);
            }
        }

        return emps;
    }

    //匿名内部类
    @Test
    public void test1(){
        List<Employee> list =  filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 22;
            }
        });

        for (Employee e : list) {
            System.out.println(e);
        }
    }

    // Lambda表达式
    @Test
    public void test2(){
        List<Employee> list =  filterEmployee(employees, employee -> employee.getAge() > 22);

        for (Employee e : list) {
            System.out.println(e);
        }
    }

    // stream API
    @Test
    public void test3(){
        employees.stream()
                .filter(e -> e.getAge()>=22)
                .limit(1)
                .forEach(System.out::println);

        employees.stream().map(employee -> employee.getName()).forEach(System.out::println);
    }

}
