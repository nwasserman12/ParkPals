
package org.launchcode.ParkPals.models;

public enum DogActivity {
    LOW("Low", 1),
    MODERATE("Moderate", 2),
    HIGH("High", 3),
    ALL("All", 0);

    private final String activityLevel;
    private final Integer activityValue;


    DogActivity(String activityLevel, Integer activityValue) {
        this.activityLevel = activityLevel;
        this.activityValue = activityValue;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public Integer getActivityValue() {
        return activityValue;
    }
}