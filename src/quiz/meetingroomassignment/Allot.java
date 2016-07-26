package quiz.meetingroomassignment;

import java.util.ArrayList;
import java.util.Iterator;

public class Allot {
    MeetingArray meetingArray = new MeetingArray();
    ArrayList<Integer> numberArray = new ArrayList<>();
    Time time = new Time();
    Meeting tmpMeeting = null;
    int start = 0;
    int end = 0;
    int number = 0;
    int diff = 0;
    int count = 0;

    Allot() {
    }

    public int counting(int number) {
        if (time.timeArray.get(start) != 1 && time.timeArray.get(end) != 1
                && time.timeArray.get((start + end) / 2) != 1) {
            for (int i = start; i <= end; i++) {
                time.timeArray.set(i, 1);
            }
            count++;
            numberArray.add(number);
        }
        if (time.timeArray.get(start) == 1 && time.timeArray.get(start + 1) == 0 && time.timeArray.get(end) == 0) {
            for (int i = start; i <= end; i++) {
                time.timeArray.set(i, 1);
            }
            count++;
            numberArray.add(number);
        }
        return count;
    }

    public void allotTo() {
        for(int i = 0 ; i< meetingArray.meetingArrayList.size();i++) {
            tmpMeeting = meetingArray.meetingArrayList.get(i); // 어떤걸 선택할 것인지?
            number = tmpMeeting.getNumber();
            start = tmpMeeting.getStartTime();
            end = tmpMeeting.getEndTime();
            diff = tmpMeeting.getDifference();
            count = counting(number);
            System.out.println(time.timeArray);
        }
        print();
    }

    public void print() {
        System.out.println(count);
        Iterator<Integer> numit = numberArray.iterator();
        while (numit.hasNext()) {
            int num = numit.next();
            System.out.print(num + " ");
        }
    }

}
