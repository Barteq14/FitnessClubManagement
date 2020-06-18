package edu.uph.ii.platformy.services;

import edu.uph.ii.platformy.controllers.commands.TrainingRoomFilter;
import edu.uph.ii.platformy.exceptions.TrainingRoomNotFoundException;
import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.TrainingRoom;
import edu.uph.ii.platformy.models.TrainingRoomType;
import edu.uph.ii.platformy.repositories.AccessoryRepository;
import edu.uph.ii.platformy.repositories.TrainingRoomRepository;
import edu.uph.ii.platformy.repositories.TrainingRoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingRoomServiceImpl implements TrainingRoomService{

    @Override
    public Page<TrainingRoom> getAllTrainingRoom(TrainingRoomFilter filter, java.awt.print.Pageable pageable) {
        return null;
    }

    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private TrainingRoomTypeRepository trainingRoomTypeRepository;

    @Autowired
    private TrainingRoomRepository trainingRoomRepository;

    @Override
    public List<Accessory> getAllAccessories() {
        return accessoryRepository.findAll();
    }

    @Override
    public List<TrainingRoomType> getAllTypes() {
        return trainingRoomTypeRepository.findAll();
    }


//    @Override
    public Page<TrainingRoom> getAllTrainingRoom(TrainingRoomFilter search, Pageable pageable) {
        Page page;
        if(search.isEmpty()){
            page = trainingRoomRepository.findAll(pageable);
        }else{
            page = trainingRoomRepository.findAllTrainingRoomUsingFilter(search.getPhraseLIKE(),search.getNumber_of_places(), pageable);
        }

        return page;

    }
    @Transactional
    @Override
    public TrainingRoom getTrainingRoom(Long id) {
        Optional<TrainingRoom> optionalTrainingRoom = trainingRoomRepository.findById(id);
        TrainingRoom trainingRoom = optionalTrainingRoom.orElseThrow(()->new TrainingRoomNotFoundException(id));
        trainingRoom.getAccessories().size();//dociągnięcie leniwych akcesoriów. Wymagana adnotacja @Transaction nad metodą lub klasą, aby findById nie zamknęło transakcji
        return trainingRoom;
    }

    @Override
    public void deleteTrainingRoom(Long id) {
        // w przypadku usuwania obsługa wyjątku VehicleNotFoundException nie jest niezbędna dla bezpieczeństwa systemu
        if(trainingRoomRepository.existsById(id) == true){
            trainingRoomRepository.deleteById(id);
        }else{
            throw new TrainingRoomNotFoundException(id);
        }
    }

    @Override
    public void saveTrainingRoom(TrainingRoom trainingRoom) {
        trainingRoomRepository.save(trainingRoom);
    }
}
