
package objects;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.animation.Animation;
 import javafx.animation.KeyFrame;
 import javafx.animation.Timeline;
 import javafx.scene.control.Label;
 import javafx.util.Duration;
import manipulater.Commonalit;

public class DigitalClock extends Commonalit{
     private static DateTimeFormatter SHORT_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
     
    public DigitalClock(Label label)
    {
        bindToTime(label);
    }

    private void bindToTime(Label label) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                                                      event -> label.setText(LocalTime.now().format(SHORT_TIME_FORMATTER))),
                                         new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    public  void setDate(Label label){
        try{
            Date dnow=new Date();
            SimpleDateFormat ft=new SimpleDateFormat("E yyyy-MM-dd");
            label.setText(""+ft.format(dnow));    
            systemDate=dnow;
        }catch(Exception ex){
        }    
    }
    
    
    
    
}
