package projecta07.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long idEmployee;

    @Column(name = "name_employee")
    private String nameEmployee;

    @Column(name = "address_employee")
    private String addressEmployee;

    @Column(name = "phone_employee")
    private String phoneEmployee;

    @Column(name = "gender_employee")
    private boolean genderEmployee;

    @Column(name = "date_of_birth_employee")
    private String dateOfBirthEmployee;

    @Column(name = "salary_employee")
    private Double salaryEmployee;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "id_position", nullable = false)
    private Position position;

    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private List<Order> orderList;

    public Employee() {}

    public Employee(Long idEmployee, String nameEmployee, String addressEmployee, String phoneEmployee, boolean genderEmployee, String dateOfBirthEmployee, Double salaryEmployee, User user1, Position position, List<Order> orderList) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.addressEmployee = addressEmployee;
        this.phoneEmployee = phoneEmployee;
        this.genderEmployee = genderEmployee;
        this.dateOfBirthEmployee = dateOfBirthEmployee;
        this.salaryEmployee = salaryEmployee;
        this.user1 = user1;
        this.position = position;
        this.orderList = orderList;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser() {
        return user1;
    }

    public void setUser(User user) {
        this.user1 = user;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public Long getId() {
        return idEmployee;
    }

    public void setId(Long id) {
        this.idEmployee = id;
    }


    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getAddressEmployee() {
        return addressEmployee;
    }

    public void setAddressEmployee(String addressEmployee) {
        this.addressEmployee = addressEmployee;
    }

    public String getPhoneEmployee() {
        return phoneEmployee;
    }

    public void setPhoneEmployee(String phoneEmployee) {
        this.phoneEmployee = phoneEmployee;
    }

    public boolean isGenderEmployee() {
        return genderEmployee;
    }

    public void setGenderEmployee(boolean genderEmployee) {
        this.genderEmployee = genderEmployee;
    }

    public String getDateOfBirthEmployee() {
        return dateOfBirthEmployee;
    }

    public void setDateOfBirthEmployee(String dateOfBirthEmployee) {
        this.dateOfBirthEmployee = dateOfBirthEmployee;
    }

    public Double getSalaryEmployee() {
        return salaryEmployee;
    }

    public void setSalaryEmployee(Double salaryEmployee) {
        this.salaryEmployee = salaryEmployee;
    }
}
