package co.istad.springwebmvc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = IDENTITY )
    private Integer id;
    @Column(unique = true,nullable = false,length = 40)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;

}
