package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table (name = "user")
@Data
@NoArgsConstructor
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

    public User(String login, String password, AccessLevel accesLvl, LocalDateTime dateOfCreation) {
        setLogin(login);
        setPassword(password);
        setAccessLvl(accesLvl);
        setDateOfCreation(dateOfCreation);
    }

    public User(Long id, String login, String password, AccessLevel accesLvl, LocalDateTime dateOfCreation, LocalDateTime dateOfModification) {
        setId(id);
        setLogin(login);
        setPassword(password);
        setAccessLvl(accesLvl);
        setDateOfCreation(dateOfCreation);
        setDateOfModification(dateOfModification);
    }
}
