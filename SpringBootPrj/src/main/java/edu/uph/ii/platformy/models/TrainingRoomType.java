package edu.uph.ii.platformy.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "trainingRoomType")
@Getter
@Setter
@NoArgsConstructor
public class TrainingRoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @Valid
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TrainingRoomType_accessory" , joinColumns = @JoinColumn(name = "trainingRoomType_id"),
            inverseJoinColumns = @JoinColumn(name = "accessory_id"))
    private Set<Accessory> accessories;

    public TrainingRoomType(String name){
        this.name=name;
    }


}
