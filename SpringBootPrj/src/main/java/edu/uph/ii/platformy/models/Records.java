package edu.uph.ii.platformy.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "record")
@Getter
@Setter
@NoArgsConstructor
public class Records {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Min(30)
    @Max(100)
    private int price;

    @Valid
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="activities_id", nullable = false)
    private Activities activities;

    @ManyToMany(mappedBy = "records")
    private Set<User> users;
/*
    @Valid
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="trainingRoom_id", nullable = false)
    private TrainingRoom trainingRoom;
*/
    public Records(String name, int price,Activities activities){
        this.name=name;
        this.price=price;
        this.activities=activities;
    }

}
