package edu.uph.ii.platformy.controllers.commands;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class TrainingRoomFilter {


        private String phrase;
        @PositiveOrZero
        private int number_of_places;



        public boolean isEmpty(){
            return StringUtils.isEmpty(phrase) && number_of_places == 0 ;
        }

        public void clear(){
            this.phrase = "";
            this.number_of_places=0;
        }

        public String getPhraseLIKE(){
            if(StringUtils.isEmpty(phrase)) {
                return null;
            }else{
                return "%"+phrase+"%";
            }
        }


    }


