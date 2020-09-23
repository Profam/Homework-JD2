package by.rabtsevich.pojo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private Integer Id;
    @Column
    private Integer age;
    @Column
    private String name;
    @Column
    private String surname;
}
