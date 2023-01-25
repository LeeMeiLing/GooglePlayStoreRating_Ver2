package gps_rating;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        
        File csvFile = new File("googleplaystore.csv");
        if (csvFile.exists()){
            System.out.println("File exists");
        }else{
            System.out.println("File not found");

        }

        String readLine;
        String[] data;
        String appName;
        String category;
        double rating;
        int categoryFirstIndex;
        String remain;
        GPSInfo gpsInfo;
        Map<String,GPSInfo> GPSRating = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){

            readLine = br.readLine(); // skip first line for header

            while((readLine = br.readLine()) != null){

                category = "";

                // skip line if empty line is read
                if(readLine.isBlank()){
                    continue;
                }

                for (int i = 0; i < Category.cat.length; i++){
                    if (readLine.contains(Category.cat[i])){
                        // System.out.println("This line contains : " + Category.cat[i]); // debug
                        category = Category.cat[i];
                        // System.out.printf("category contains %d character \n" , category.length()); // debug
                        break;
                    }
                }

                if(category.equals("")){
                    continue;
                }

                // find category first index
                categoryFirstIndex = readLine.indexOf(category);
                // System.out.println("Category first index = " + categoryFirstIndex); //debug
                appName = readLine.substring(0, categoryFirstIndex-1); // remove ,
                // System.out.println(appName); //debug                
                remain = readLine.substring(categoryFirstIndex + category.length() + 1);
                data = remain.split(",",2 );
                // System.out.println(remain); //debug
                // System.out.println("data[0]: " + data[0]); //debug
                // System.out.println("data[1]: " +data[1]); //debug

                // if (!data[0].equals("NaN")){

                try {
                    rating = Double.parseDouble(data[0]);
                    if(Double.isNaN(rating)){
                        continue;
                    }
                } catch (Exception e) {
                    // System.out.println("catch " + data[0]); // debug
                    continue;
                }

                // System.out.println("rating is: " + rating); // debug

                if(!GPSRating.containsKey(category)) {

                gpsInfo = new GPSInfo(category);
                gpsInfo.setAverageRating(rating);
                gpsInfo.setHighestRating(appName, rating);
                gpsInfo.setLowestRating(appName, rating);

                GPSRating.put(category, gpsInfo);  

                }else{
                    
                    gpsInfo = GPSRating.get(category);
                    gpsInfo.setAverageRating(rating);
                    gpsInfo.setHighestRating(appName, rating);
                    gpsInfo.setLowestRating(appName, rating);

                    GPSRating.put(category, gpsInfo); // update GPSRating  

                }

                
           
            }

            for (String key : GPSRating.keySet()){
                System.out.println(key);
                System.out.println(GPSRating.get(key));
                System.out.println("===============================================================");
            }

            System.out.println("Total category = " + GPSRating.size());

        }

    }
}