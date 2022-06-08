package projecta07.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_role")
    private Long idUserRole;
    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonBackReference(value = "UserRole_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserRole() {
    }

    public UserRole(Long idUserRole) {
        this.idUserRole = idUserRole;
    }

    public Long getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(Long idUserRole) {
        this.idUserRole = idUserRole;
    }
}
