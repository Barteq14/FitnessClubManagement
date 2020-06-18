package edu.uph.ii.platformy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such vehicle")
public class TrainingRoomNotFoundException extends RuntimeException{

    public TrainingRoomNotFoundException(){
        super(String.format("Sala nie istnieje"));
    }

    public TrainingRoomNotFoundException(Long id){
        super(String.format("Sala o id %d nie istnieje", id));
    }
}
