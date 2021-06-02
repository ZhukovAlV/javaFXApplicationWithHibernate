package entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table (name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name="accessLvl")
    private AccessLevel accessLvl;

    @Column(name = "dateOfCreation")
    private LocalDateTime dateOfCreation;

    @Column(name = "dateOfModification")
    private LocalDateTime dateOfModification;
}
