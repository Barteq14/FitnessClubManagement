package edu.uph.ii.platformy.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
public class Activities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String name;

    @Max(50)
    private int number_of_places;

    @NotBlank
    private String day_of_the_week;

    private int price;

    private Time startTime;
    private Time endTime;
    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="trainingRoom_id", nullable = false)
    private TrainingRoom trainingRoom;

    public Activities(String name, int number_of_places, String day_of_the_week,Time startTime,Time endTime,TrainingRoom trainingRoom){
        this.name=name;
        this.number_of_places=number_of_places;
        this.day_of_the_week=day_of_the_week;
        this.endTime=endTime;
        this.startTime=startTime;
        this.trainingRoom=trainingRoom;
    }


}
