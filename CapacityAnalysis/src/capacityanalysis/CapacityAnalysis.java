/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package capacityanalysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.io.File;
import java.time.*;
import java.util.*;

/**
 *
 * @author tchase
 */
public class CapacityAnalysis {

    /**
     * @param args the command line arguments
     */
    public int numSites;
    public List<Integer> siteID = new ArrayList<Integer>();
    public List<Integer> numLanes = new ArrayList<Integer>();
    public List<Double> FFS = new ArrayList<Double>();
    public List<Double> m = new ArrayList<Double>();
    public List<Double> b = new ArrayList<Double>();
    public List<String> type = new ArrayList<String>();

    public List<LocalDateTime> datetime = new ArrayList<LocalDateTime>();
    public List<Double> inrix1 = new ArrayList<Double>();
    public List<Double> inrix2 = new ArrayList<Double>();
    public List<Double> inrix3 = new ArrayList<Double>();
    public List<Double> sensor1Speed = new ArrayList<Double>();
    public List<Double> sensro1Flow = new ArrayList<Double>();
    public List<Double> sensor2Speed = new ArrayList<Double>();
    public List<Double> sensro2Flow = new ArrayList<Double>();
    public List<Double> sensor3Speed = new ArrayList<Double>();
    public List<Double> sensro3Flow = new ArrayList<Double>();

    public static void main(String[] args) {
        CapacityAnalysis obj = new CapacityAnalysis();
        obj.run();
    }

    public void run() {
        String dataDirectory = selectDirectory();
        
        importSummary(dataDirectory + "\\Summary Data.csv");

        testPrintSummary();
        
//        importSummary(dataDirectory + "\\Summary Data.csv");

        for (int i = 1; i <= numSites; i++) {
            for (int j = 1; j <= 3; j++) { //Type of Dataset (1=HERE+INRIX, 2=Single Sensor, 3=Multiple Sensor)
                System.out.println(dataDirectory + "\\" + j + "-" + String.format("%02d", i) + ".csv");
//                importRawData(dataDirectory + "\\" + j + "-" + String.format("%02d", i)+".csv");
            }
        }
    }

    private void testPrintSummary() {
        System.out.println("siteID:");
        for(Object obj : siteID){
            System.out.println("* "+obj);
        }
        System.out.println("numLanes:");
        for(Object obj : numLanes){
            System.out.println("* "+obj);
        }
        System.out.println("FFS:");
        for(Object obj : FFS){
            System.out.println("* "+obj);
        }
        System.out.println("m:");
        for(Object obj : m){
            System.out.println("* "+obj);
        }
        System.out.println("b:");
        for(Object obj : b){
            System.out.println("* "+obj);
        }
        System.out.println("type:");
        for(Object obj : type){
            System.out.println("* "+obj);
        }
    }

    public void importSummary(String filename) {
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";

            try {
                int i = 0;
                br = new BufferedReader(new FileReader(filename));
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] row = line.split(cvsSplitBy);
                    if (i >= 1) {
                        siteID.add(Integer.parseInt(row[0]));
//                        numLanes.add(Integer.parseInt(row[7]));
                        numLanes.add(4);
                        FFS.add(Double.parseDouble(row[2]));
                        m.add(Double.parseDouble(row[3]));
                        b.add(Double.parseDouble(row[4]));
//                        type.add(row[6]) ;
                        type.add(row[6]);
                    }

                    i++;
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void importRawData(String filename) {
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            BufferedReader br = null;
            String line = "";
            String cvsSplitBy = ",";

            try {

                br = new BufferedReader(new FileReader(filename));
                while ((line = br.readLine()) != null) {

                    // use comma as separator
                    String[] row = line.split(cvsSplitBy);

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public String selectDirectory() {
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        f.showSaveDialog(null);
        return f.getSelectedFile().getPath();
    }
}
