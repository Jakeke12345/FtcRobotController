package org.firstinspires.ftc.teamcode.teamcalamari.lib.Auto.DriverOriented;
//DONE WITHOUT DATALOGGERS!!!!!!!!!
import android.os.Environment;
import android.util.Log;

import org.firstinspires.ftc.teamcode.teamcalamari.lib.Measurements.Angle;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class IMU_Logging {
    public static double parseHeadingFile(String season_Name){
        try {
            FileReader fr = new FileReader(String.format("%s/DataLogger/%s.txt", Environment.getExternalStorageDirectory().getPath(), season_Name));
            StringBuilder builder = new StringBuilder();
            int i;
            while ((i = fr.read()) != -1) {
                builder.append((char) i);
            }
            String[] lines = builder.toString().split("\n");
            Log.v(String.format("%sUtils", season_Name), Arrays.toString(lines));
            return Double.parseDouble(lines[0]);
        }
        catch (IOException e){
            System.err.println("Driver oriented file is corrupted or does not exist. Maybe this has something to do with Hampter???");
            return 0;
        }
    }

    public static void saveHeadingFile(Angle endingHeading, String season_Name){
        String folderPath = Environment.getExternalStorageDirectory().getPath()+"/DataLogger";
        String filePath = String.format("%s/DataLogger/%s.txt", Environment.getExternalStorageDirectory().getPath(), season_Name);
        boolean fileCreated = new File(folderPath).mkdir();
        if (fileCreated){
            System.out.printf("File \"%s\" was just created", filePath);
        } else {
            System.out.printf("File \"%s\" was already exists", filePath);
        }
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(endingHeading.toString());
            writer.close();
        } catch (IOException e) {
            System.err.printf("File \"%s\" has been edited by an unknown source or is corrupted", filePath);
        }
    }
}
