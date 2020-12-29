import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
   Google interview problem from youtube

 */

public class MeetingsListDp {

    static class MeetingsContainer {
        int hours;
        List<Integer> ids;
        MeetingsContainer(List<Integer> ids, int hours){
            this.hours = hours;
            this.ids = ids;
        }
    }

    static class Meeting {
        String name;
        int hours;
        public Meeting ( String name, int hours ){
            this.name = name;
            this.hours = hours;
        }
        public String toString(){
            return name + ":" + hours;
        }
    }

    static MeetingsContainer maxMeeting(MeetingsContainer meeting1, MeetingsContainer meeting2){
        return ( meeting1.hours > meeting2.hours ) ? meeting1 : meeting2;
    }

    public static MeetingsContainer optimizeMeetingsUtil(List<Meeting> meetings, int hours, int idx, Stack<Integer> ids, int total, MeetingsContainer[][] mem ){
        if ( idx == meetings.size() ) {
            return new MeetingsContainer( new ArrayList<>(ids), total );
        }
        if ( mem[hours][idx] != null ) return mem[hours][idx];

        Meeting meeting = meetings.get(idx);
        if (  meeting.hours > hours ) {
            return mem[hours][idx] = optimizeMeetingsUtil( meetings, hours , idx+1, ids, total, mem );
        }
        else {
            MeetingsContainer exclude = optimizeMeetingsUtil( meetings, hours , idx+1, ids, total, mem );
            ids.push(idx);
            MeetingsContainer include = optimizeMeetingsUtil( meetings, hours-meeting.hours, idx+1, ids, total+meeting.hours,mem );
            ids.pop();
            return mem[hours][idx] = maxMeeting(include, exclude);
        }
    }

    public static List<Meeting> optimizeMeetings( List<Meeting> meetings, int hours ){
        MeetingsContainer meetingsContainer = optimizeMeetingsUtil(  meetings, hours, 0, new Stack<>(), 0, new MeetingsContainer[hours+1][meetings.size()+1] );
        List<Meeting> scheduledMeetings = new ArrayList<>();
        for ( Integer id:  meetingsContainer.ids ) {
            scheduledMeetings.add( meetings.get(id)  );
        }
        return scheduledMeetings;
    }

    public static void main(String[] args){
        int total =  8;
        List<Meeting> meetings = new ArrayList<>();
        meetings.add(new Meeting("meeting1",5));
        meetings.add(new Meeting("meeting2",5));
        meetings.add(new Meeting("meeting3",3));
        System.out.println(optimizeMeetings( meetings, total ));
    }
}
