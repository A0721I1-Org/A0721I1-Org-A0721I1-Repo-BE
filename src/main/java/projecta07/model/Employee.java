package projecta07.model;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long idEmployee;

    @Column(name = "name_employee")
    @NotEmpty(message = "không được để trống")
    private String nameEmployee;

    @Column(name = "address_employee")
    @NotEmpty(message = "không được để trống")
    private String addressEmployee;

    @Column(name = "phone_employee")
    @NotEmpty(message = "không được để trống")
    private String phoneEmployee;

    @Column(name = "gender_employee")
//    @NotEmpty(message = "không được để trống")
    private boolean genderEmployee;

    @Column(name = "date_of_birth_employee")
    @NotEmpty(message = "không được để trống")
    private String dateOfBirthEmployee;

    @Column(name = "salary_employee")
    @NotNull(message = "không được để trống")
    @Min(value = 100000, message = "mức lương thấp nhất là 100,000")
    private Double salaryEmployee;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "employe_user")
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
//    @NotEmpty(message = "không được để trống")
//    @JsonBackReference(value = "employee_user1")
    private User user;

    @ManyToOne(targetEntity = Position.class)
    @JoinColumn(name = "id_position", nullable = false)
    private Position position;

    @OneToMany(mappedBy = "employee")
    @JsonBackReference(value = "employee_orderList")
    private List<Order> orderList;

    public Employee() {
        //khoi tao constructor khong tham so
    }
    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user1) {
        this.user = user1;
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
