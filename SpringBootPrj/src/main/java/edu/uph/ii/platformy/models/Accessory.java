package edu.uph.ii.platformy.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="accessories")
@Data
@NoArgsConstructor
public class Accessory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    private String name;

    @ManyToMany(mappedBy = "accessories")
    private Set<TrainingRoomType> trainingRoomTypes;

    public Accessory(String name){
        this.name = name;
    }

}
