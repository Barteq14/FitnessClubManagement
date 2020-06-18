package edu.uph.ii.platformy.config;

import edu.uph.ii.platformy.models.*;
import edu.uph.ii.platformy.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private ActivitiesRepository activitiesRepository;
    @Autowired
    private RecordsRepository recordsRepository;
    @Autowired
    private TrainingRoomRepository trainingRoomRepository;
    @Autowired
    private TrainingRoomTypeRepository trainingRoomTypeRepository;
    @Autowired
    private AccessoryRepository accessoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    InitializingBean init() {

        return () -> {




            if(roleRepository.findAll().isEmpty() == true){
                try {

                    //tworzenie akcesoriów
                    Accessory ac1 = new Accessory("sprzet1");
                    accessoryRepository.save(ac1);
                    Accessory ac2 = new Accessory("sprzet2");
                    accessoryRepository.save(ac2);
                    Accessory ac3 = new Accessory("sprzet2");
                    accessoryRepository.save(ac3);

                    //tworzenie typow sal treningowych
                    TrainingRoomType trt1 = new TrainingRoomType("Do fitness");
                    trt1.setAccessories(new HashSet<>(Arrays.asList(ac1)));
                    trainingRoomTypeRepository.save(trt1);
                    TrainingRoomType trt2 = new TrainingRoomType("Do ciezarow");
                    trt2.setAccessories(new HashSet<>(Arrays.asList(ac3)));
                    trainingRoomTypeRepository.save(trt2);
                    TrainingRoomType trt3 = new TrainingRoomType("Do areobikow");
                    trt3.setAccessories(new HashSet<>(Arrays.asList(ac2)));
                    trainingRoomTypeRepository.save(trt3);

                    //tworzenie sali
                    TrainingRoom tr1 = new TrainingRoom("Silownia",50,trt1);
                    trainingRoomRepository.save(tr1);
                    TrainingRoom tr2 = new TrainingRoom("Fitness",50,trt2);
                    trainingRoomRepository.save(tr2);
                    TrainingRoom tr3 = new TrainingRoom("Sala wspinaczkowa",50,trt3);
                    trainingRoomRepository.save(tr3);


                    //tworzenie zajęć
                    Activities a1 = new Activities("Zajeciafitness",50,"Monday",new Time(00-00-00),new Time(00-00-00),tr1);
                    activitiesRepository.save(a1);
                    Activities a2 = new Activities("Zajeciaaerobik",50,"Saturday",new Time(00-00-00),new Time(00-00-00),tr2);
                    activitiesRepository.save(a2);
                    Activities a3 = new Activities("Zajeciasilownia",50,"Friday",new Time(00-00-00),new Time(00-00-00),tr3);
                    activitiesRepository.save(a3);

                    //tworzenie zapisow
                    Records r1 = new Records("Zapis1",100,a1);
                    recordsRepository.save(r1);
                    Records r2 = new Records("Zapis2",60,a2);
                    recordsRepository.save(r2);
                    Records r3 = new Records("Zapis3",80,a3);
                    recordsRepository.save(r3);


                    //tworzenie roli
                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));


                    //tworzenie użytkowników
                    User user = new User("user", "user","Bartek","Longota","b.longot2@wp.pl",new Date(9-9-2019),new Date(8-8-1996),true);
                    user.setRoles(new HashSet<>(Arrays.asList(roleUser)));
                    user.setRecords(new HashSet<>(Arrays.asList(r1)));//tworzymy zestaw na podstawie tablicy pobranej za pomoca metody asList()
                    user.setPassword(passwordEncoder.encode("user"));

                    User admin = new User("admin", "admin","Marek","marek","mareczek@wp.pl",new Date(2-2-2019),new Date(8-8-1996),true);
                    admin.setRoles(new HashSet<>(Arrays.asList(roleAdmin)));
                    admin.setRecords(new HashSet<>(Arrays.asList(r2)));
                    admin.setPassword(passwordEncoder.encode("admin"));

                    User test = new User("test","test", "test","test","test@op.pl",new Date(12-12-2019),new Date(8-8-1996),true);
                    test.setRoles(new HashSet<>(Arrays.asList(roleAdmin, roleUser)));
                    test.setRecords(new HashSet<>(Arrays.asList(r1,r2)));
                    test.setPassword(passwordEncoder.encode("test"));

                    userRepository.save(user);
                    userRepository.save(admin);
                    userRepository.save(test);



                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        };
    }

}
