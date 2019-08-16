package input;

import org.junit.Before;
import org.junit.Test;
import repositories.AthleteInputRepo;
import validators.ValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class TriathlonAthleteTest {
    TriathlonAthlete triathlonAthlete;

    String content = "11a,,SjK,30:2x7,xxxoax,xxxxx,xxoxo";

    String content2 = "27,\"Piotr Smitzer\",\"CZ\",30:10,xxxxx,xxxxx,xxxxx\n" +
            "13,Popescu Vasilica,RO,31:01,xxxxx,xxxox,xxxxx";

    String content3 = "22,\"Piotr Smitzer\",\"CZ\",30:10,xxxxx,xxxxx,xxxxx\n";

    List<AthleteInputRepo> athleteList = new ArrayList<>();
    String[] athleteLine;

    @Before
    public void setup() throws IOException {
        triathlonAthlete = new TriathlonAthlete();
        String line = "";
        String csvValueSeparator = ",";

        BufferedReader csvReader = new BufferedReader(new StringReader(content));


        while ((line = csvReader.readLine()) != null) {

            //verificari si compuneri
            athleteLine = line.replaceAll("\"", "").split(csvValueSeparator);
        }
    }

    @Test(expected = ValidationException.class)
    public void testAddingWithInvalidNumber() throws ValidationException {

        triathlonAthlete.checkNumber(athleteLine[0], "...");
        fail("should have thrown something");
    }

    @Test(expected = ValidationException.class)
    public void testAddingWithInvalidName() throws ValidationException {

        triathlonAthlete.checkName(athleteLine[1], "...");
        fail("should have thrown something");
    }


    @Test(expected = ValidationException.class)
    public void testAddingWithInvalidCountryCode() throws ValidationException {

        triathlonAthlete.checkCountryCode(athleteLine[2], "...");
        fail("should have thrown something");
    }

    @Test(expected = ValidationException.class)
    public void testAddingWithInvalidTime() throws ValidationException {

        triathlonAthlete.getTimeResult(athleteLine[3], "...");
        fail("should have thrown something");
    }

    @Test(expected = ValidationException.class)
    public void testAddingWithInvalidTargets() throws ValidationException {

        triathlonAthlete.checkTargetResult(athleteLine[4], "...");
        fail("should have thrown something");
    }

    @Test
    public void testAddingWithValidTargets() throws ValidationException {


        assertThat(triathlonAthlete.checkTargetResult(athleteLine[5], "..."), is("xxxxx"));
    }

    @Test
    public void testAddingInputs() throws ValidationException, IOException {
        BufferedReader csvReader = new BufferedReader(new StringReader(content2));

        triathlonAthlete.getListOfAthletesFromCsv(csvReader);

        assertThat(triathlonAthlete.athleteList.size(), is(2));
    }

}