package edu.uph.ii.platformy.repositories;

import edu.uph.ii.platformy.models.TrainingRoom;
import edu.uph.ii.platformy.models.TrainingRoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.validation.constraints.Past;

public interface TrainingRoomRepository extends JpaRepository<TrainingRoom,Long> , JpaSpecificationExecutor {

    //nazwa metody jest jednocześnie zapytaniem
    Page<TrainingRoom> findByNameContaining(String phrase, Pageable pageable);


    //nad klasą Vehicle znajduje się definicja zapytania (@NamedQuery) powiązana z tą metodą
    Page<TrainingRoom> findAllTrainingRoomUsingNamedQuery(String phrase, Pageable pageable);

    @Query("SELECT tr FROM TrainingRoom tr WHERE " +
            "(" +
            ":phrase is null OR :phrase = '' OR "+
            "upper(tr.name) LIKE upper(:phrase) OR " +
            "upper(tr.trainingRoomType.name) LIKE upper(:phrase)" +
            ") " +
            "AND (:max is null OR :max >= tr.number_of_places)")
    Page<TrainingRoom> findAllTrainingRoomUsingFilter(@Param("phrase") String p, @Param("max") int number_of_places, Pageable pageable);

}
