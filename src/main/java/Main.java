import repositories.StandingsRepo;
import validators.ValidationException;

public class Main {
    public static void main(String[] args) {

        StandingsRepo standings = new StandingsRepo();
        try {
            standings.listFinalStandings();
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}
