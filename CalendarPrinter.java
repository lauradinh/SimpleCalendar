import java.util.ArrayList;
import java.util.Scanner;

public class CalendarPrinter {

  /**
   * Calculates the number of centuries (rounded down) between year 0 and the specified year. For
   * example, the year 2034 has the century index of 20 (as do the other years 2000-2099).
   * 
   * @param year to compute the century offset for
   * @return number of centuries between year 0 and the specified year
   */
  public static int fullCenturiesContained(Year year) {
    int intYear = year.intValue();
    int century = intYear / 100;
    return century;
  }

  /**
   * Calculates the number of years between the specified year and the first year of its century.
   * For example, the year 2034 has the offset within its century of 34 (as do 1234 and 9999934).
   * 
   * @param year to compute the offset within century for
   * @return number of years between the specified year and the first year of century
   */
  public static int yearOffsetWithinCentury(Year year) {
    int intYear = year.intValue();
    int offsetYears = intYear % 100;
    return offsetYears;
  }

  /**
   * This method computes whether the specified year is a leap year or not.
   * 
   * @param year is the year is being checked for leap-year-ness
   * @return true when the specified year is a leap year, and false otherwise
   */
  public static boolean isLeapYear(Year year) {
    int intYear = year.intValue();
    if (intYear % 4 != 0) {
      return false;
    } else if (intYear % 100 != 0) {
      return true;
    } else if (intYear % 400 != 0) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Calculates the number of days in the specified month, while taking into consideration whether
   * or not the specified month is in a leap year. Note: that this is calculated based on the
   * month's monthOfYear and year, and is NOT retrieved from the month's getDayCount() method. This
   * is because this method is used to generate the day objects that are stored within each month.
   * 
   * @param month to determine the number of days within (within its year)
   * @return the number of days in the specified month (between 28-31)
   */
  public static int numberOfDaysInMonth(Month month) {
    if (month.getMonthOfYear().equals(MonthOfYear.January)
        || month.getMonthOfYear().equals(MonthOfYear.March)
        || month.getMonthOfYear().equals(MonthOfYear.May)
        || month.getMonthOfYear().equals(MonthOfYear.July)
        || month.getMonthOfYear().equals(MonthOfYear.August)
        || month.getMonthOfYear().equals(MonthOfYear.October)
        || month.getMonthOfYear().equals(MonthOfYear.December)) {
      return 31;
    } else if (month.getMonthOfYear().equals(MonthOfYear.April)
        || month.getMonthOfYear().equals(MonthOfYear.June)
        || month.getMonthOfYear().equals(MonthOfYear.September)
        || month.getMonthOfYear().equals(MonthOfYear.November)) {
      return 30;
    } else if (month.getMonthOfYear().equals(MonthOfYear.February) && isLeapYear(month.getYear())) {
      return 29;
    } else {
      return 28;
    }
  }

  /**
   * Calculates which day of the week the first day of the specified month falls on. Note: that this
   * is calculated based on the month's monthOfYear and year, and is NOT retrieved from the month's
   * getDayByDate(1) day. This is because this method is used to generate the day objects that are
   * stored within each month.
   * 
   * @param month within which we are calculate the day of week for the first day
   * @return DayOfWeek corresponding to the first day within the specified month
   */
  public static DayOfWeek calcFirstDayOfWeekInMonth(Month month) {
    int day = 0;
    int ordinal = month.getMonthOfYear().ordinal();
    int offSet = yearOffsetWithinCentury(month.getYear());
    int century = fullCenturiesContained(month.getYear());

    if (ordinal <= 1) {
      ordinal += 13;
      if (offSet == 0) {
        century--;
        offSet = 99;
      } else {
        offSet--;
      }
    } else {
      ordinal++;
    }
    // Formula for calculating day of week
    day = (1 + (13 * (ordinal + 1) / 5) + offSet + (offSet / 4) + (century / 4) + 5 * century) % 7;
    switch (day) {
      case 0:
        return DayOfWeek.Saturday;
      case 1:
        return DayOfWeek.Sunday;
      case 2:
        return DayOfWeek.Monday;
      case 3:
        return DayOfWeek.Tuesday;
      case 4:
        return DayOfWeek.Wednesday;
      case 5:
        return DayOfWeek.Thursday;
      default:
        return DayOfWeek.Friday;
    }
  }

  /**
   * Calculates the day of the week that follows the specified day of week. For example, Thursday
   * comes after Wednesday, and Monday comes after Sunday.
   * 
   * @param dayBefore is the day of week that comes before the day of week returned
   * @return day of week that comes after dayBefore
   */
  public static DayOfWeek dayOfWeekAfter(DayOfWeek dayBefore) {
    int ordinal = dayBefore.ordinal() + 1;
    if (ordinal > 6) {
      ordinal = 0;
    }
    switch (ordinal) {
      case 0:
        return DayOfWeek.Monday;
      case 1:
        return DayOfWeek.Tuesday;
      case 2:
        return DayOfWeek.Wednesday;
      case 3:
        return DayOfWeek.Thursday;
      case 4:
        return DayOfWeek.Friday;
      case 5:
        return DayOfWeek.Saturday;
      default:
        return DayOfWeek.Sunday;
    }
  }

  /**
   * Calculates the month of the year that follows the specified month. For example, July comes
   * after June, and January comes after December.
   * 
   * @param monthBefore is the month that comes before the month that is returned
   * @return month of year that comes after monthBefore
   */
  public static MonthOfYear monthOfYearAfter(MonthOfYear monthBefore) {
    int ordinal = monthBefore.ordinal() + 1;
    if (ordinal > 11) {
      ordinal = 0;
    }
    switch (ordinal) {
      case 0:
        return MonthOfYear.January;
      case 1:
        return MonthOfYear.February;
      case 2:
        return MonthOfYear.March;
      case 3:
        return MonthOfYear.April;
      case 4:
        return MonthOfYear.May;
      case 5:
        return MonthOfYear.June;
      case 6:
        return MonthOfYear.July;
      case 7:
        return MonthOfYear.August;
      case 8:
        return MonthOfYear.September;
      case 9:
        return MonthOfYear.October;
      case 10:
        return MonthOfYear.November;
      default:
        return MonthOfYear.December;
    }
  }

  /**
   * Creates a new month object and fully initializes with its corresponding days.
   * 
   * @param monthOfYear which month of the year this new month represents
   * @param year        in which this month is a part
   * @return reference to the newly created month object
   */
  public static Month createNewMonth(MonthOfYear monthOfYear, Year year) {
    Month createMonth = new Month(monthOfYear, year);
    int numDays = numberOfDaysInMonth(createMonth);
    DayOfWeek tempDay = calcFirstDayOfWeekInMonth(createMonth);
    createMonth.addDay(new Day(tempDay, 1, createMonth));
    for (int i = 2; i <= numDays; i++) {
      Day createDay = new Day(dayOfWeekAfter(tempDay), i, createMonth);
      createMonth.addDay(createDay);
      tempDay = dayOfWeekAfter(tempDay);
    }
    return createMonth;
  }

  /**
   * Prints the contents of the specified month object in calendar form. This printout should begin
   * with the Month an year of the month. The next line should contain the three letter
   * abbreviations for the seven days of the week. And then the dates of each day of that month
   * should follow: one week per line, with periods in positions of days that are a part of the
   * month before or after. For example, January 2020 should be printed as follows:
   *
   * January 2020 MON TUE WED THU FRI SAT SUN . . 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
   * 21 22 23 24 25 26 27 28 29 30 31 . .
   *
   * @param month which is to be printed by this method
   */
  public static void printMonth(Month month) {
    System.out.println(month.getMonthOfYear() + " " + month.getYear());
    System.out.println("MON TUE WED THU FRI SAT SUN");
    int numDays = numberOfDaysInMonth(month);
    DayOfWeek currentDay = calcFirstDayOfWeekInMonth(month);
    int ordinal = currentDay.ordinal();
    for (int i = 0; i < ordinal; i++) {
      System.out.print(" .  ");
    }
    for (int i = 1; i <= numDays; i++) {
      if (i < 10) {
        System.out.print(" " + i + "  ");
      } else {
        System.out.print(" " + i + " ");
      }
      if (dayOfWeekAfter(currentDay) == DayOfWeek.Monday) {
        System.out.println();
      }
      currentDay = dayOfWeekAfter(currentDay);
    }
    if (currentDay != DayOfWeek.Sunday) {
      int lastOrdinal = currentDay.ordinal();
      for (int i = lastOrdinal; i < 7; i++) {
        System.out.print(" .  ");
      }
    }
    System.out.println();
  }

  /**
   * Creates an array list of months that are initialized with their full complement of days. Prints
   * out each of these months in calendar form, and then returns the resulting ArrayList.
   * 
   * @param month of the year for the first month that is created and printed
   * @param year  that the first month created and printed is a part of
   * @param count is the total number of sequential months created and printed
   * @return the array list of months that this method generates and prints.
   */
  public static ArrayList<Month> createAndPrintMonths(MonthOfYear month, Year year, int count) {
    ArrayList<Month> viewMonths = new ArrayList<Month>();
    MonthOfYear currentMonth = monthOfYearAfter(month);
    Year currentYear = year;
    viewMonths.add(createNewMonth(month, year));
    for (int i = 1; i < count; i++) {
      if (currentMonth == MonthOfYear.January) {
        currentYear = new Year(year.intValue() + 1);
      }
      viewMonths.add(createNewMonth(currentMonth, currentYear));
      currentMonth = monthOfYearAfter(currentMonth);
    }
    for (int i = 0; i < count; i++) {
      printMonth(viewMonths.get(i));
    }

    return viewMonths;
  }

  /**
   * Driver for the CalendarPrinter Application. This program asks the user to enter an initial
   * month, an initial year, and the total number of months to create and display in calendar form.
   * 
   * @param args is not used by this program
   */
  public static void main(String[] args) {
    // print welcome message
    Scanner in = new Scanner(System.in);
    System.out.println("Welcome to the Calendar Printer.");
    System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
    // read input from the user
    System.out.print("Enter the month to begin calendar: ");
    String monthString = in.nextLine().trim();
    System.out.print("Enter the year to begin calendar: ");
    String yearString = in.nextLine().trim();
    System.out.print("Enter the number of months to include in this calendar: ");
    String countString = in.nextLine().trim();
    // convert user input into usable form
    monthString = monthString.substring(0, 3).toUpperCase();
    MonthOfYear month = null;
    for (int i = 0; i < MonthOfYear.values().length; i++)

      if (monthString.equals(MonthOfYear.values()[i].name().substring(0, 3).toUpperCase()))
        month = MonthOfYear.values()[i];
    Year year = new Year(Integer.parseInt(yearString.trim()));
    int count = Integer.parseInt(countString.trim());
    // create months and display them in calendar form
    System.out.println();
    createAndPrintMonths(month, year, count);
    // display thank you message
    System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
    System.out.println("Thanks, and have a nice day.");
    in.close();
  }
}
