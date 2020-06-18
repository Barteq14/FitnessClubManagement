package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.TrainingRoomFilter;
import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.TrainingRoom;
import edu.uph.ii.platformy.models.TrainingRoomType;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface TrainingRoomService {

    List<Accessory> getAllAccessories();

    List<TrainingRoomType> getAllTypes();

    Page<TrainingRoom> getAllTrainingRoom(TrainingRoomFilter filter, Pageable pageable);

    TrainingRoom getTrainingRoom(Long id);

    void deleteTrainingRoom(Long id);

    void saveTrainingRoom(TrainingRoom trainingRoom);
}
