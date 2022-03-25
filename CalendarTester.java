import java.util.ArrayList;
import java.util.Scanner;

public class CalendarTester {

  /**
   * Checks whether CalendarPrinter.fullCenturiesContained() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testFullCenturiesContained() {
    if (CalendarPrinter.fullCenturiesContained(new Year(2)) != 0)
      return false;
    if (CalendarPrinter.fullCenturiesContained(new Year(204)) != 2)
      return false;
    if (CalendarPrinter.fullCenturiesContained(new Year(300)) != 3)
      return false;
    if (CalendarPrinter.fullCenturiesContained(new Year(2020)) != 20)
      return false;
    if (CalendarPrinter.fullCenturiesContained(new Year(44444)) != 444)
      return false;
    if (CalendarPrinter.fullCenturiesContained(new Year(888888)) != 8888)
      return false;
    return true;
  }

  /**
   * Checks whether CalendarPrinter.yearOffsetWithinCentury() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testYearOffsetWithinCentury() {
    if (CalendarPrinter.yearOffsetWithinCentury(new Year(2)) != 2)
      return false;
    if (CalendarPrinter.yearOffsetWithinCentury(new Year(2040)) != 40)
      return false;
    if (CalendarPrinter.yearOffsetWithinCentury(new Year(44444)) != 44)
      return false;
    return true;
  }

  /**
   * Checks whether CalendarPrinter.isLeapYear() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testIsLeapYear() {
    if (!CalendarPrinter.isLeapYear(new Year(1936)))
      return false;
    if (CalendarPrinter.isLeapYear(new Year(2001)))
      return false;
    if (!CalendarPrinter.isLeapYear(new Year(1984)))
      return false;
    if (!CalendarPrinter.isLeapYear(new Year(2000)))
      return false;
    return true;
  }

  /**
   * Checks whether CalendarPrinter.numberOfDaysInMonth() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testNumberOfDaysInMonth() {
    if (CalendarPrinter.numberOfDaysInMonth(new Month(MonthOfYear.February, new Year(2020))) != 29)
      return false;
    if (CalendarPrinter.numberOfDaysInMonth(new Month(MonthOfYear.November, new Year(1950))) != 30)
      return false;
    if (CalendarPrinter.numberOfDaysInMonth(new Month(MonthOfYear.February, new Year(2007))) != 28)
      return false;
    return true;
  }

  /**
   * Checks whether CalendarPrinter.calcFirstDayOfWeekInMonth() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCalcFirstDayOfWeekInMonth() {
    if (CalendarPrinter.calcFirstDayOfWeekInMonth(
        new Month(MonthOfYear.April, new Year(1984))) != DayOfWeek.Sunday)
      return false;
    if (CalendarPrinter.calcFirstDayOfWeekInMonth(
        new Month(MonthOfYear.January, new Year(2020))) != DayOfWeek.Wednesday)
      return false;
    if (CalendarPrinter.calcFirstDayOfWeekInMonth(
        new Month(MonthOfYear.February, new Year(2005))) != DayOfWeek.Tuesday)
      return false;
    if (CalendarPrinter.calcFirstDayOfWeekInMonth(
        new Month(MonthOfYear.June, new Year(2000))) != DayOfWeek.Thursday)
      return false;
    return true;
  }

  /**
   * Checks whether CalendarPrinter.dayOfWeekAfter() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testDayOfWeekAfter() {
    if (CalendarPrinter.dayOfWeekAfter(DayOfWeek.Friday) != DayOfWeek.Saturday)
      return false;
    if (CalendarPrinter.dayOfWeekAfter(DayOfWeek.Sunday) != DayOfWeek.Monday)
      return false;
    if (CalendarPrinter.dayOfWeekAfter(DayOfWeek.Thursday) != DayOfWeek.Friday)
      return false;
    return true;
  }

  /**
   * Checks whether CalenderPrinter.monthOfYearAfter() method works as expected.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testMonthOfYearAfter() {
    if (CalendarPrinter.monthOfYearAfter(MonthOfYear.December) != MonthOfYear.January)
      return false;
    if (CalendarPrinter.monthOfYearAfter(MonthOfYear.June) != MonthOfYear.July)
      return false;
    if (CalendarPrinter.monthOfYearAfter(MonthOfYear.March) != MonthOfYear.April)
      return false;
    return true;
  }

  /**
   * Checks whether CalendarPrinter.createNewMonth() method works as expect.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testCreateNewMonth() {
    Month tester = CalendarPrinter.createNewMonth(MonthOfYear.March, new Year(2020));
    if (tester.getDayByDate(1).getDate() != 1
        && tester.getDayByDate(1).getDayOfWeek() != DayOfWeek.Sunday
        && Integer.parseInt(tester.getYear().toString()) != 2020) {
      return false;
    }
    if (tester.getDayByDate(31).getDate() != 31
        && tester.getDayByDate(31).getDayOfWeek() != DayOfWeek.Tuesday
        && Integer.parseInt(tester.getYear().toString()) != 2020) {
      return false;
    }
    if (tester.getDayByDate(18).getDate() != 8
        && tester.getDayByDate(18).getDayOfWeek() != DayOfWeek.Wednesday
        && Integer.parseInt(tester.getYear().toString()) != 2020) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    // System.out.println("fullCenturiesContainedTest(): " + testFullCenturiesContained());
    // System.out.println("yearOffsetWithinCenturyTest(): " + testYearOffsetWithinCentury());
    // System.out.println("isLeapYearTest(): " + testIsLeapYear());
    // System.out.println("numberOfDaysInMonthTest(): " + testNumberOfDaysInMonth());
    // System.out.println("calcFirstDayOfWeekInMonthTest(): " + testCalcFirstDayOfWeekInMonth());
    // System.out.println("dayOfWeekAfterTest(): " + testDayOfWeekAfter());
    // System.out.println("monthOfYearAfterTest(): " + testMonthOfYearAfter());
    // System.out.println("createNewMonthTest(): " + testCreateNewMonth());
  }

}
