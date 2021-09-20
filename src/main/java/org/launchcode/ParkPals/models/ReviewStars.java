package org.launchcode.ParkPals.models;

public enum ReviewStars {

        ONE("One star"),
        TWO("Two stars"),
        THREE("Three stars"),
        FOUR("Four stars"),
        FIVE("Five stars");

        private final String stars;

        ReviewStars(String stars) {
            this.stars = stars;
        }

        public String getStars() {
            return stars;
        }

}
