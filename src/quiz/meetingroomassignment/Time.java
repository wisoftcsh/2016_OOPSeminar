package quiz.meetingroomassignment;

import java.util.ArrayList;

public class Time {
    ArrayList<Integer> timeArray = null;
    Time(){
        creatTimeArray();
    }

    public void creatTimeArray(){
        timeArray = new ArrayList<>();
        for(int i = 0; i< 25 ; i++){
            timeArray.add(0);
        }
        timeArray.set(0, 9);//0번째는 더미
    }
}
