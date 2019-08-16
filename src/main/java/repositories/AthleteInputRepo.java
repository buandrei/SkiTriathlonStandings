package repositories;

public class AthleteInputRepo {

    int athleteNumber;
    String athleteName;
    String countryCode;
    SkiTimeResults skiTimeResults;
    String firstShooting;
    String secondShooting;
    String thirdShooting;

    public AthleteInputRepo(int athleteNumber, String athleteName, String countryCode, SkiTimeResults skiTimeResults, String firstShooting, String secondShooting, String thirdShooting) {
        this.athleteNumber = athleteNumber;
        this.athleteName = athleteName;
        this.countryCode = countryCode;
        this.skiTimeResults = skiTimeResults;
        this.firstShooting = firstShooting;
        this.secondShooting = secondShooting;
        this.thirdShooting = thirdShooting;
    }

    public int getAthleteNumber() {
        return athleteNumber;
    }

    public void setAthleteNumber(int athleteNumber) {
        this.athleteNumber = athleteNumber;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public void setAthleteName(String athleteName) {
        this.athleteName = athleteName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public SkiTimeResults getSkiTimeResults() {
        return skiTimeResults;
    }

    public void setSkiTimeResults(SkiTimeResults skiTimeResults) {
        this.skiTimeResults = skiTimeResults;
    }

    public String getFirstShooting() {
        return firstShooting;
    }

    public void setFirstShooting(String firstShooting) {
        this.firstShooting = firstShooting;
    }

    public String getSecondShooting() {
        return secondShooting;
    }

    public void setSecondShooting(String secondShooting) {
        this.secondShooting = secondShooting;
    }

    public String getThirdShooting() {
        return thirdShooting;
    }

    public void setThirdShooting(String thirdShooting) {
        this.thirdShooting = thirdShooting;
    }
}
