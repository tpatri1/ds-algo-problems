package com.trinath.dsalgo.lb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://www.youtube.com/watch?v=lG6HxO7cDRw
public class LowestCommonManager_Rec {
    public static OrgChart getLowestCommonManager(
            OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
        // Write your code here.
        return getLowestCommonManagerHelper(topManager,reportOne, reportTwo).lowestCommmonManager;
    }
    public static OrgInfo getLowestCommonManagerHelper(OrgChart manager, OrgChart reportOne, OrgChart reportTwo) {
       int numOfImportantReports = 0;
       //Base case
        if(manager==reportOne || manager==reportTwo) {
            numOfImportantReports += 1;
        }
       for(OrgChart directReport: manager.directReports){
            OrgInfo orgInfo = getLowestCommonManagerHelper(directReport,reportOne,reportTwo); // this is a non tail recursion so goes to the leaf and then next sentences are evaluated from leaf to top
            if(orgInfo.lowestCommmonManager!=null){
                return orgInfo;
            }
           numOfImportantReports+= orgInfo.numberOfImpReports; // these numbers are added from bottom to top when recursive call falls from top of stack to bottom
       }
        OrgChart lcm =numOfImportantReports==2 ? manager:null;
        OrgInfo orgInfo = new OrgInfo(lcm,numOfImportantReports);

        return orgInfo;
    }
    static class OrgChart {
        public char name;
        public List<OrgChart> directReports;

        OrgChart(char name) {
            this.name = name;
            this.directReports = new ArrayList<OrgChart>();
        }


        // This method is for testing only.
        public void addDirectReports(OrgChart[] directReports) {
            for (OrgChart directReport : directReports) {
                this.directReports.add(directReport);
            }
        }
    }
    static class OrgInfo{
        public OrgChart lowestCommmonManager;
        public int numberOfImpReports;
        public OrgInfo(OrgChart lowestCommmonManager, int numberOfImpReports){
            this.lowestCommmonManager = lowestCommmonManager;
            this.numberOfImpReports = numberOfImpReports;
        }
    }
    public static void main(String args[]){
         Map<Character, OrgChart> orgCharts;
         String alphabet;


            orgCharts = new HashMap<Character, OrgChart>();
            alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (char a : alphabet.toCharArray()) {
                orgCharts.put(a, new LowestCommonManager_Rec.OrgChart(a));
            }

            orgCharts
                    .get('A')
                    .addDirectReports(
                            new LowestCommonManager_Rec.OrgChart[] {
                                    orgCharts.get('B'),
                                    orgCharts.get('C'),
                                    orgCharts.get('D'),
                                    orgCharts.get('E'),
                                    orgCharts.get('F')
                            });
            orgCharts
                    .get('B')
                    .addDirectReports(
                            new LowestCommonManager_Rec.OrgChart[] {orgCharts.get('G'), orgCharts.get('H'), orgCharts.get('I')});
            orgCharts.get('C').addDirectReports(new LowestCommonManager_Rec.OrgChart[] {orgCharts.get('J')});
            orgCharts
                    .get('D')
                    .addDirectReports(new LowestCommonManager_Rec.OrgChart[] {orgCharts.get('K'), orgCharts.get('L')});
            orgCharts
                    .get('F')
                    .addDirectReports(new LowestCommonManager_Rec.OrgChart[] {orgCharts.get('M'), orgCharts.get('N')});
            orgCharts
                    .get('H')
                    .addDirectReports(
                            new LowestCommonManager_Rec.OrgChart[] {
                                    orgCharts.get('O'), orgCharts.get('P'), orgCharts.get('Q'), orgCharts.get('R')
                            });
            orgCharts.get('K').addDirectReports(new LowestCommonManager_Rec.OrgChart[] {orgCharts.get('S')});
            orgCharts
                    .get('P')
                    .addDirectReports(new LowestCommonManager_Rec.OrgChart[] {orgCharts.get('T'), orgCharts.get('U')});
            orgCharts.get('R').addDirectReports(new LowestCommonManager_Rec.OrgChart[] {orgCharts.get('V')});
            orgCharts
                    .get('V')
                    .addDirectReports(
                            new LowestCommonManager_Rec.OrgChart[] {orgCharts.get('W'), orgCharts.get('X'), orgCharts.get('Y')});
            orgCharts.get('X').addDirectReports(new LowestCommonManager_Rec.OrgChart[] {orgCharts.get('Z')});

    LowestCommonManager_Rec.OrgChart lcm =
            LowestCommonManager_Rec.getLowestCommonManager(orgCharts.get('A'), orgCharts.get('W'), orgCharts.get('B'));

    System.out.println(lcm.name);
    }

}
