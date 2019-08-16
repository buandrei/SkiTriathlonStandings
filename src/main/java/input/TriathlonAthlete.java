package input;


import repositories.AthleteInputRepo;
import repositories.SkiTimeResults;
import validators.ValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TriathlonAthlete {


    public List<AthleteInputRepo> athleteList = new ArrayList<>();

    public TriathlonAthlete() {
    }

    public void getListOfAthletesFromCsv(Reader reader) throws IOException, ValidationException {


        String line = "";
        String csvValueSeparator = ",";
        String[] athleteLine;
        BufferedReader csvReader = new BufferedReader(reader);


        while ((line = csvReader.readLine()) != null) {

            //verificari si compuneri
            athleteLine = line.replaceAll("\"", "").split(csvValueSeparator);


            AthleteInputRepo athlete = new AthleteInputRepo(
                    checkNumber(athleteLine[0], line),
                    checkName(athleteLine[1], line),
                    checkCountryCode(athleteLine[2], line),
                    getTimeResult(athleteLine[3], line),
                    checkTargetResult(athleteLine[4], line),
                    checkTargetResult(athleteLine[5], line),
                    checkTargetResult(athleteLine[6], line)
            );

            athleteList.add(athlete);


        }

    }

    public static int checkNumber(String s, String line) throws ValidationException {
        if (!isInteger(s)) {
            throw new ValidationException("Invalid number at line " + line);
        }
        return Integer.parseInt(s);
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public static String checkCountryCode(String s, String line) throws ValidationException {
        if (s.length() != 2) {
            throw new ValidationException("Country Code invalid at line " + line);
        }
        return s;
    }

    public static SkiTimeResults getTimeResult(String s, String line) throws ValidationException {
        String[] timeSplit = s.split(":");

        if (timeSplit.length != 2) {
            throw new ValidationException("Null or Multiple double dots found for time at line " + line);
        }

        if (!isInteger(timeSplit[0])) {
            throw new ValidationException("Invalid time at line " + line);
        }
        if (!isInteger(timeSplit[1])) {
            throw new ValidationException("Invalid number at line " + line);
        }


        int minutes = Integer.parseInt(timeSplit[0]);
        int seconds = Integer.parseInt(timeSplit[1]);

        if (seconds > 60 && seconds < 0) {
            throw new ValidationException("Invalid seconds format at line " + line);
        }

        if (minutes < 0) {
            throw new ValidationException("Invalid minutes format at line " + line);
        }

        SkiTimeResults result = new SkiTimeResults(minutes, seconds);
        return result;
    }

    public static String checkTargetResult(String s, String line) throws ValidationException {
        if (s.length() != 5) {
            throw new ValidationException("Something went wrong on the target practice result.Lenght is not 5(shootings). Invalid format at line " + line);
        }
        if (!s.matches("[xo]+")) {
            throw new ValidationException("The target result contains invalid characters (other than 'x' or 'o'. Pleas check line " + line);
        }

        return s;
    }

    public static String checkName(String s, String line) throws ValidationException {
        if (s.isBlank()) {
            throw new ValidationException("The name of the contestant is empty at line" + line);
        }
        return s;
    }

    public List<AthleteInputRepo> getAthleteList() {
        return athleteList;
    }
}
