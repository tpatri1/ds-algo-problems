package com.trinath.dsalgo.lb.Uncategorized;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CalendarMatching {
    public static List<StringMeeting> calendarMatching(List<StringMeeting> calendar1, StringMeeting dailyBounds1,
                                                       List<StringMeeting> calendar2, StringMeeting dailyBounds2, int meetingDuration) {
        //Meeting dailyBounds = getMergedBounds(dailyBounds1,dailyBounds2);
        calendar1.add(0,new StringMeeting("0:00",dailyBounds1.start));
        calendar1.add(calendar1.size(), new StringMeeting(dailyBounds1.end, "23:59"));
        List<Meeting> cal1 = convertToMeetings(calendar1);
        calendar2.add(0,new StringMeeting("0:00",dailyBounds2.start));
        calendar2.add(calendar2.size(), new StringMeeting(dailyBounds2.end, "23:59"));
        List<Meeting> cal2 = convertToMeetings(calendar2);
        List<Meeting> available = new ArrayList<>();
        List<Meeting> mergedCal = mergeCalandars(cal1, cal2);
         available = getAvailable(mergedCal, meetingDuration);
        return convertToStringMeetings(available);
    }

    private static List<Meeting> getAvailable(List<Meeting> mergedCal, int duration) {
        List<Meeting> available = new ArrayList<>();
        for(int i=1; i<mergedCal.size(); i++){
            if(mergedCal.get(i).start-mergedCal.get(i-1).end >=duration)
            available.add(new Meeting(mergedCal.get(i-1).end, mergedCal.get(i).start));
        }
        return  available;
    }

    private static List<Meeting> mergeCalandars(List<Meeting> cal1, List<Meeting> cal2){
        List<Meeting> merged =  new ArrayList<>();
        int i=0;
        int j=0;
        int start =-1;
        int end =-1;
        while(i<cal1.size() && j<cal2.size()){
            if(cal1.get(i).start<=cal2.get(j).start){
                merged.add(cal1.get(i));
                i++;
            }else{
                merged.add(cal2.get(j));
                j++;
            }
        }
        List<Meeting> flattend =  new ArrayList<>();
        Meeting meeting = null;
        for(int k= 0; k<merged.size(); k++){
            if(meeting==null){
                meeting = new Meeting(merged.get(k).start, merged.get(k).end);
                continue;
            }
            if(merged.get(k).start>=meeting.start &&merged.get(k).end<=meeting.end){
                continue;
            }else if(merged.get(k).start>=meeting.start && merged.get(k).start<=meeting.end && merged.get(k).end>meeting.end){
                meeting.end = merged.get(k).end;
            }else{
                flattend.add(meeting);
                meeting = new Meeting(merged.get(k).start, merged.get(k).end);;
            }
        }
        flattend.add(meeting);
        return flattend;
    }

    private static boolean isWithinBounds(Meeting curr, Meeting mergedBounds){
        return curr.start>=mergedBounds.start && curr.end<=mergedBounds.end;
    }

    static class StringMeeting {
        public String start;
        public String end;

        public StringMeeting(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }

    private static List<Meeting> convertToMeetings(List<StringMeeting> stringMeetings){
        //return stringMeetings.stream().map(m->transform(m)).collect(Collectors.toList());
        List<Meeting> res = new ArrayList<>();
        for(StringMeeting stringMeeting: stringMeetings){
            res.add(transform(stringMeeting));
        }
        return res;
    }
    private static List<StringMeeting> convertToStringMeetings(List<Meeting> meetings){
        //return stringMeetings.stream().map(m->transform(m)).collect(Collectors.toList());
        List<StringMeeting> res = new ArrayList<>();
        for(Meeting meeting: meetings){
            res.add(revTransform(meeting));
        }
        return res;
    }

    private static Meeting transform(StringMeeting strMeeting){
        return new Meeting(parseTimeToNumber(strMeeting.start), parseTimeToNumber(strMeeting.end));
    }
    private static StringMeeting revTransform(Meeting meeting){
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        start.append(meeting.start /60).append(":").append(meeting.start %60==0?"00":meeting.start %60);
        end.append(meeting.end /60).append(":").append(meeting.end %60==0?"00":meeting.end %60);
        return new StringMeeting(start.toString(), end.toString());
    }

    private static int parseTimeToNumber(String time){
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0])*60+Integer.valueOf(parts[1]);
    }


    static class Meeting{
        public int start;
        public int end;
        public Meeting(int start, int end){
            this.start = start;
            this.end = end;
        }
        public Meeting(){

        }
    }

    public static void main(String args[]){

        StringMeeting stringMeeting =  new StringMeeting("8:30","10:30");
        Meeting meeting = transform(stringMeeting);
        System.out.println(" str meeting "+stringMeeting.start+" "+stringMeeting.end+" "+meeting.start+" "+meeting.end);
        StringMeeting stringMeeting1 =  new StringMeeting("9:00","10:30");
        StringMeeting stringMeeting2 =  new StringMeeting("12:00","13:00");
        StringMeeting stringMeeting3 =  new StringMeeting("16:00","18:00");
        StringMeeting stringMeeting4 =  new StringMeeting("16:30","19:30");
        StringMeeting bounds1 =  new StringMeeting("9:00","20:00");
        StringMeeting stringMeeting11 =  new StringMeeting("10:00","11:30");
        StringMeeting stringMeeting12 =  new StringMeeting("12:30","14:30");
        StringMeeting stringMeeting13 =  new StringMeeting("14:30","15:00");
        StringMeeting stringMeeting14 =  new StringMeeting("16:00","17:00");
        StringMeeting bounds2 =  new StringMeeting("10:00","18:30");
        List<StringMeeting> cal1 = new ArrayList<>();
        cal1.add(stringMeeting1);
        cal1.add(stringMeeting2);
        cal1.add(stringMeeting3);
        //cal1.add(stringMeeting4);

        List<StringMeeting> cal2 = new ArrayList<>();
        cal2.add(stringMeeting11);
        cal2.add(stringMeeting12);
        cal2.add(stringMeeting13);
        cal2.add(stringMeeting14);
        calendarMatching(cal1,bounds2,cal2,bounds1,30);


    }
}
