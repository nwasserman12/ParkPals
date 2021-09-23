package org.launchcode.ParkPals.models;

public enum ReviewStars {

        ONE("One star", 1),
        TWO("Two stars", 2),
        THREE("Three stars", 3),
        FOUR("Four stars", 4),
        FIVE("Five stars", 5);

        private final String stars;
        private final Integer starValue;

        ReviewStars(String stars, Integer starValue) {
            this.stars = stars;
            this.starValue = starValue;
        }

        public String getStars() {
            return stars;
        }

        public Integer getStarValue() {
                return starValue;
        }
}
