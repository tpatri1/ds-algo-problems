package com.trinath.dsalgo.lb;
import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;

public class CalanderMatching {


        public static List<StringMeeting> calendarMatching(List<StringMeeting> calendar1, StringMeeting dailyBounds1,
                                                           List<StringMeeting> calendar2, StringMeeting dailyBounds2, int meetingDuration) {
            //Meeting dailyBounds = getMergedBounds(dailyBounds1,dailyBounds2);
            calendar1.add(0,new StringMeeting("0:00",dailyBounds1.start));//Mark it blocked from mid night to start
            calendar1.add(calendar1.size(), new StringMeeting(dailyBounds1.end, "23:59"));//mark the end to midnight blocked
            List<Meeting> cal1 = convertToMeetings(calendar1);
            calendar2.add(0,new StringMeeting("0:00",dailyBounds2.start));
            calendar2.add(calendar2.size(), new StringMeeting(dailyBounds2.end, "23:59"));
            List<Meeting> cal2 = convertToMeetings(calendar2);
            List<Meeting> available = new ArrayList<>();
            List<Meeting> mergedCal = mergeCalandars(cal1, cal2);
            available = getAvailable(mergedCal, meetingDuration);
            return convertToStringMeetings(available);
        }
//Find two calendar's available time
        private static List<Meeting> getAvailable(List<Meeting> mergedCal, int duration) {
            List<Meeting> available = new ArrayList<>();
            for(int i=1; i<mergedCal.size(); i++){
                if(mergedCal.get(i).start-mergedCal.get(i-1).end >=duration)
                    available.add(new Meeting(mergedCal.get(i-1).end, mergedCal.get(i).start));
            }
            return  available;
        }
//Merge two calender's blocked time
        private static List<Meeting> mergeCalandars(List<Meeting> cal1, List<Meeting> cal2){
            List<Meeting> merged =  new ArrayList<>();
            int i=0;
            int j=0;
            int start =-1;
            int end =-1;
            //Sorting based on the start date and putting in one list - merged
            while(i<cal1.size() && j<cal2.size()){
                if(cal1.get(i).start<=cal2.get(j).start){
                    merged.add(cal1.get(i));
                    i++;
                }else{
                    merged.add(cal2.get(j));
                    j++;
                }
            }
            //Actual merge overlapping intervals - flattened
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

        public static void main(String[] args){
            //Sort a hasMap bu=y key(using TreeMap) and value(there is no direct way to do this)
            Map<String,String> map = new HashMap<>();
            map.put("foo2","bar2");
            map.put("foo","bar");
            map.put("12345","12345");
            map.put("foo1","bar1");
            map.put("1234","1234");
            Comparator<Map.Entry<String,String>> comparator = (e1,e2)-> e1.getValue().compareTo(e2.getValue());

            //map = map.entrySet().stream().sorted(comparator).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue ));
            List<Map.Entry<String,String>> list = new ArrayList<>(map.entrySet());
            Collections.sort(list, (a,b)->a.getValue().compareTo(b.getValue()));
            HashMap<String, String> temp = new LinkedHashMap<String, String>();
            for (Map.Entry<String, String> aa : list) {
                temp.put(aa.getKey(), aa.getValue());
            }


            for(String key:temp.keySet()){
                System.out.println(" key "+key+" value"+map.get(key));
            }
        }
    }


