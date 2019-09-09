package hello;

import java.util.HashMap;

public class Driver {

    final int age;
    final int priorClaims;

    public int getAge() {
        return age;
    }

    public int getPriorClaims() {
        return priorClaims;
    }

    public String getLocationRiskProfile() {
        return locationRiskProfile;
    }

    final String locationRiskProfile;


    public Driver(int age, int priorClaims, String locationRiskProfile) {
        this.age = age;
        this.priorClaims = priorClaims;
        this.locationRiskProfile = locationRiskProfile;
    }

    public Driver(HashMap<String, Object> input) {
        this.age = (int) (double) input.get("age");
        this.priorClaims = (int) (double) input.get("priorClaims");
        this.locationRiskProfile = (String) input.get("locationRiskProfile");
    }
}
