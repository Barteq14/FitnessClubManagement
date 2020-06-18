package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.models.Accessory;
import edu.uph.ii.platformy.models.TrainingRoom;
import edu.uph.ii.platformy.models.TrainingRoomType;
import edu.uph.ii.platformy.services.TrainingRoomService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names={"trainingRoomType", "accessoryList", "trainingRoom"})
@Log4j2
public class TrainingRoomFormController {

    private TrainingRoomService trainingRoomService;

    //Wstrzyknięcie zależności przez konstruktor. Od wersji 4.3 Springa nie trzeba używać adnontacji @Autowired, gdy mamy jeden konstruktor
    @Autowired
    public TrainingRoomFormController(TrainingRoomService trainingRoomService)
    {
        this.trainingRoomService = trainingRoomService;
    }


    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/trainingRoomForm.html", method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){

        model.addAttribute("trainingRoom",
                id.isPresent()?
                        trainingRoomService.getTrainingRoom(id.get()):
                        new TrainingRoom());

        return "trainingRoomForm";
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(value="/trainingRoomForm.html", method= RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String processForm(@Valid @ModelAttribute("trainingRoom") TrainingRoom trainingRoom, BindingResult errors){

        if(errors.hasErrors()){
            return "trainingRoom";
        }

        //log.info("Data utworzenia komponentu "+v.getCreatedDate());
        //log.info("Data edycji komponentu "+new Date());

        trainingRoomService.saveTrainingRoom(trainingRoom);

        return "redirect:trainingRoomList.html";//po udanym dodaniu/edycji przekierowujemy na listę
    }

    @ModelAttribute("trainingRoomType")
    public List<TrainingRoomType> loadTypes(){
        List<TrainingRoomType> types = trainingRoomService.getAllTypes();
       // log.info("Ładowanie listy "+types.size()+" typów ");
        return types;
    }

    @ModelAttribute("accessoryList")
    public List<Accessory> loadAccessories(){
        List<Accessory> accessories = trainingRoomService.getAllAccessories();
        //log.info("Ładowanie listy "+accessories.size()+" akcesoriów ");
        return accessories;
    }
}
