package webBoard.korean.dto;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name= "USERS")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    /*
    @Override
    public String toString() {
        return "User(id=" + id + ", name=" + name + ", email=" + email + ")";
    }
    */
}

