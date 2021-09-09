
package org.launchcode.ParkPals.models;

public enum DogActivity {
    LOW("Low"),
    MODERATE("Moderate"),
    HIGH("High"),
    ALL("All");

    private final String activityLevel;

    DogActivity(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getActivityLevel() {
        return activityLevel;
    }
}