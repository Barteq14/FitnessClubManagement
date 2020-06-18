package edu.uph.ii.platformy.controllers;


import edu.uph.ii.platformy.controllers.commands.TrainingRoomFilter;
import edu.uph.ii.platformy.services.TrainingRoomService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DecimalFormat;

@Controller
@SessionAttributes("searchCommand")
@Log4j2
public class TrainingRoomListController {

    @Autowired
    private TrainingRoomService trainingRoomService;

    @Secured("IS_AUTHENTICATED_FULLY")
    @RequestMapping(value="/trainingRoomList.html", params = "id", method = RequestMethod.GET)
    public String showTrainingRoomDetails(Model model, Long id){
        log.info("Pokazywanie szczegółów");
        model.addAttribute("trainingRoom", trainingRoomService.getTrainingRoom(id));
        return "trainingRoomDetails";
    }

   @GetMapping(value="/errors")
    public String resetTrainingRoomList(){
       return "redirect:trainingRoomList.html";
    }


    @ModelAttribute("searchCommand")
    public TrainingRoomFilter getSimpleSearch(){
        return new TrainingRoomFilter();
    }

    @GetMapping(value="/trainingRoomList.html", params = {"all"})
    public String resetTrainingRoomList(@ModelAttribute("searchCommand") TrainingRoomFilter search){
        search.clear();
        return "redirect:trainingRoomList.html";
    }

    @RequestMapping(value="/trainingRoomList.html", method = {RequestMethod.GET, RequestMethod.POST})
    public String showTrainingRoomList(Model model, @Valid @ModelAttribute("searchCommand") TrainingRoomFilter search, BindingResult result, Pageable pageable){
        model.addAttribute("trainingRoomListPage", trainingRoomService.getAllTrainingRoom(search, (java.awt.print.Pageable) pageable));
        return "trainingRoomList";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping(path="/trainingRoomList.html", params={"did"})
    public String deleteTrainingRoom(long did, HttpServletRequest request){
        trainingRoomService.deleteTrainingRoom(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:trainingRoomList.html%s", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }

    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        if (queryString.contains("&")) {
            return "?"+queryString.substring(queryString.indexOf("&") + 1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
        }else{
            return "";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {//Rejestrujemy edytory właściwości
        DecimalFormat numberFormat = new DecimalFormat("#0.00");
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(false);
        CustomNumberEditor number_of_placesEditor = new CustomNumberEditor(int.class, numberFormat, true);
        binder.registerCustomEditor(int.class, "maxNumber_of_places", number_of_placesEditor);


    }
}