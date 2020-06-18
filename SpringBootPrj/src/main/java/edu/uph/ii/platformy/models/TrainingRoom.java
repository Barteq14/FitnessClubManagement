package edu.uph.ii.platformy.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "training_room")
@NamedQuery(name = "TrainingRoom.findAllTrainingRoomUsingNamedQuery",
        query = "SELECT tr FROM TrainingRoom tr WHERE upper(tr.name) LIKE upper(:phrase) or upper(tr.name) LIKE upper(:phrase) or upper(tr.trainingRoomType.name) LIKE upper(:phrase)")

@Getter
@Setter

public class TrainingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Max(50)
    private int number_of_places;


    public TrainingRoom(){}

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Accessory> accessories;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="trainingRoomType_id", nullable = false)
    private TrainingRoomType trainingRoomType;





    public TrainingRoom(String name, int number_of_places, TrainingRoomType trainingRoomType){
        this.name=name;
        this.number_of_places=number_of_places;
        this.trainingRoomType=trainingRoomType;
    }
}
