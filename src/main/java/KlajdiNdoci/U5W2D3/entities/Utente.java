package KlajdiNdoci.U5W2D3.entities;

import KlajdiNdoci.U5W2D3.entities.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataDiNascita;
    private String avatar;
    @CreationTimestamp
    private Date createdAt;
    @OneToMany(mappedBy = "utente", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @ToString.Exclude
    private List<Post> posts;
}
