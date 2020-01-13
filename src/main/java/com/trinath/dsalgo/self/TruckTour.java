//public class TruckTour {
//
//    static class PetrolPumps{
//        int petrol;
//        int distance;
//
//        PetrolPumps(int p, int d){
//            petrol = p;
//            distance =d;
//        }
//    }
//
//    static int truckTours(PetrolPumps[] pumps){
//        int start =0;
//        int end =1;
//    static int truckToursAlt(PetrolPumps[] pumps){
//        int start =0;
//        int end =1;
//        int curr_petrol =0;
//        int len =pumps.length;
//        for(int i=0; i<len; i++){
//            curr_petrol += pumps[i].petrol - pumps[i].distance;
//            //skip that i that gives -ve current petrol
//            if(curr_petrol<0){
//                start = i+1;
//            }
//        }
//        return start;
//    }
//    public static void main(String[] args)
//    {
//
//        PetrolPumps[] arr = {new PetrolPumps(2, 3),
//                new PetrolPumps(6, 6),
//                new PetrolPumps(7, 3)};
//
//        int start = truckToursAlt(arr);
//
//        System.out.println(start == -1 ? "No Solution" : "Start = " + start);
//
//    }
//
//}
