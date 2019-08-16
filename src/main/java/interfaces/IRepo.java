package interfaces;

import repositories.AthleteInputRepo;
import validators.ValidationException;


import java.io.FileNotFoundException;
import java.util.List;

public interface IRepo<T> {
    List<AthleteInputRepo> getListOfAthletes() throws FileNotFoundException, ValidationException;

    List<AthleteInputRepo> calculateAndListTotalTimeOfAthletes() throws FileNotFoundException, ValidationException;

    void listFinalStandings() throws FileNotFoundException, ValidationException;

}
