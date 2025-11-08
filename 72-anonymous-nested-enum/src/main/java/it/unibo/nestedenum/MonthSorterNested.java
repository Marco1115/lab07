package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder();
    }

    static enum Month {

        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int monthDaysNumber;

        private Month(final int days) {
            this.monthDaysNumber = days;
        }

        public static Month fromString(final String monthName) {
            boolean foundMonth = false;
            Month result = null;
            Objects.requireNonNull(monthName);
            for(final Month month: Month.values()) {
                if(month.name().toLowerCase().startsWith(monthName.toLowerCase())) {
                    if(!foundMonth) {
                        result = month;
                        foundMonth = true;
                    } else {
                        throw new IllegalArgumentException("Ambiguos month name");
                    }
                }
            }
            if(foundMonth) {
                return result;
            } else {
                throw new IllegalArgumentException("No correnponding month name");
            }
        }

        public int getMonthDaysNumber() {
            return monthDaysNumber;
        }
    }

    class SortByMonthOrder implements Comparator<String> {
        @Override
        public int compare(String monthName0, String monthName1) {
            return Month.fromString(monthName0).compareTo(Month.fromString(monthName1));    
        }
    }

    class SortByDate implements Comparator<String> {
        @Override
        public int compare(String monthName0, String monthName1) {
            return Integer.compare(Month.fromString(monthName0).getMonthDaysNumber(), Month.fromString(monthName1).getMonthDaysNumber());
        }

    }
}
