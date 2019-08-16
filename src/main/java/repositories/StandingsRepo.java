package repositories;

import input.TriathlonAthlete;
import interfaces.IRepo;
import validators.ValidationException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class StandingsRepo implements IRepo {

    public StandingsRepo() {
    }

    @Override
    public List<AthleteInputRepo> getListOfAthletes() throws ValidationException {

        TriathlonAthlete listOfAthletes = new TriathlonAthlete();
        try {
            listOfAthletes.getListOfAthletesFromCsv(new FileReader("input_files/biathlonAthlete.csv"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return listOfAthletes.getAthleteList();
    }

    @Override
    public List<AthleteInputRepo> calculateAndListTotalTimeOfAthletes() throws ValidationException {

        List<AthleteInputRepo> unorederList = getListOfAthletes();
        for (AthleteInputRepo athleteInputRepo : unorederList) {
            //calculating total time
            long totalPenalties = countTotalPenalties(athleteInputRepo.getFirstShooting(), athleteInputRepo.getSecondShooting(), athleteInputRepo.getThirdShooting());
            long totalPenaltyTime = totalPenalties * 10;

            SkiTimeResults finalTimeResult = athleteInputRepo.getSkiTimeResults();
            finalTimeResult.addTime(totalPenaltyTime);
            athleteInputRepo.setSkiTimeResults(finalTimeResult);

        }

        return unorederList.stream().sorted(new Comparator<AthleteInputRepo>() {
            @Override
            public int compare(AthleteInputRepo o1, AthleteInputRepo o2) {
                return o1.getSkiTimeResults().totalTime() - o2.getSkiTimeResults().totalTime();
            }
        }).collect(Collectors.toList());

    }

    public static long countTotalPenalties(String firstTarget, String secondTarget, String thirdTarget) {
        long count = 0;
        count = firstTarget.chars().filter(ch -> ch == 'o').count();
        count = count + secondTarget.chars().filter(ch -> ch == 'o').count();
        count = count + thirdTarget.chars().filter(ch -> ch == 'o').count();

        return count;
    }

    @Override
    public void listFinalStandings() throws ValidationException {
        List<AthleteInputRepo> finalList = calculateAndListTotalTimeOfAthletes();
        if (finalList.size() != 0) {
            System.out.println("FINAL STANDINGS");
        }

        for (int i = 0; i <= finalList.size(); i++) {

            if (i == 1) {
                System.out.println("\n FIRST PLACE");
                congratulateWinners(finalList, 0);
            }
            if (i == 2) {
                System.out.println("\n RUNNER-UP");
                congratulateWinners(finalList, 1);
            }
            if (i == 3) {
                System.out.println("\n THIRD PLACE");
                congratulateWinners(finalList, 2);
                System.out.println("\n And special thanks to the other participants!");
                for (int j = 3; j < finalList.size(); j++) {
                    System.out.println(
                            finalList.get(j).getAthleteName() + " from " + finalList.get(j).getCountryCode()
                    );
                }
            }
        }
    }

    private void congratulateWinners(List<AthleteInputRepo> finalList, int index) {
        System.out.println(finalList.get(index).getAthleteName() + " from " + finalList.get(index).getCountryCode());
        System.out.println("With a total time of: " + finalList.get(index).getSkiTimeResults().getTotalTime());
    }


}
