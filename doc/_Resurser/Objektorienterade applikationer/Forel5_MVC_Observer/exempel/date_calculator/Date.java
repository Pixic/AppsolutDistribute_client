import java.util.Observable;

/**
 * An abstract data type for dates á la MS Excel.
 * This class provides various methods for calculating basic arithmetic operations
 * with date objects.
 * A date is internally represented as the number of days since 1900-01-01.
 * 
 * @author Uno Holmer
 * @version 2010-09-27
 */
public class Date extends Observable {
    
    public class DateDetails {
        private int y,m,d,dNo;
        public DateDetails() {
            y = year;
            m = month;
            d = day;
            dNo = dayNo;
        }
        public int getYear() { return y; }
        public int getMonth() { return m; }   
        public int getDay() { return d; }
        public int getDayNo() { return dNo; }
    }
    
    private static final int monthDays[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
    private long daysSince1900_01_01;
    private int year,month,day,dayNo;
    
    /**
     * Date - construct a date from given ymd components.
     *
     * @param  year   the year part of this Date object.
     *                The argument must satisfy the relation year >= 1900.
     * @param  month  the month part of this Date object.
     *                The argument must be in the interval [1,12].
     *                
     * @param  day    the day part of this Date object.
     *                The argument must satisfy the relation 1 <= day <= m
     *                where m denotes the number of days in the given month
     *                in the given year.
     *                
     * @throws        IllegalStateException if any of the arguments violates
     *                the validity of the constructed date.
     */   
    public Date(int year,int month,int day) throws IllegalStateException {
        checkDate(year,month,day);
        this.year = year;
        this.month = month;
        this.day = day;
        dayNo = computeDayNo(); 
        daysSince1900_01_01 = computeDaysBeforeThisYear() + dayNo;
    }
    
    /**
     * Date - construct a date from a given day number.
     *                This constructor is provided for internal use only.
     *                
     * @param  daysSince1900_01_01  the number of days since 1900-01-01.
     *                The argument must be > 0. The value 1 denotes 1900-01-01.
     *                
     * @throws        IllegalArgumentException if the argument violates
     *                the validity of the constructed date.
     */
    private Date(long daysSince1900_01_01) throws IllegalArgumentException {
        checkDayNo(daysSince1900_01_01);
        this.daysSince1900_01_01 = daysSince1900_01_01;
        computeDetails();
    }
    
    /**
     * minus - calculates a date before the date represented by this object.
     *
     * @param  days   the number of days to subtract. If days < 0, -days is added
     * @return        A new Date object representing a date the given number of 
     *                days before the date represented by this object.
     * @throws        IllegalArgumentException if the result of the operation
     *                would denote a date before 1900-01-01.
     */   
    public Date minus(long days) throws IllegalArgumentException {
        if ( days == 0 )
            return this;

        long d = daysSince1900_01_01 - days;
        checkDayNo(d);
        return new Date(d);
    }
    
    /**
     * plus - calculates a date after the date represented by this object.
     *
     * @param  days   the number of days to add. If days < 0, -days is subtracted
     *                as defined by the minus method.
     * @return        A new Date object representing a date the given number of 
     *                days after the date represented by this object.
     */
    public Date plus(long days) {
        return minus(-days);
    }

    /**
     * subtract - changes the date representation in this object to an earlier date.
     *
     * @param  days   the number of days to subtract. If days < 0, -days is added.
     *                If days != 0 the operation will mutate the calling object.
     *                Date objects are Observable, hence all observers will be notified.
     * @throws        IllegalArgumentException if the result of the operation
     *                would denote a date before 1900-01-01.
     */ 
    public void subtract(long days) throws IllegalArgumentException {
        if ( days != 0 ) {
            long d = daysSince1900_01_01 - days;
            checkDayNo(d);
            daysSince1900_01_01 = d;
            computeDetails();
            setChanged();
            notifyObservers(getDetails());
        }
    }
    
    /**
     * add - changes the date representation in this object to a later date.
     *
     * @param  days   the number of days to add. If days < 0, -days is subtracted.
     *                If days != 0 the operation will mutate the calling object.
     *                Date objects are Observable, hence all observers will be notified.
     */ 
    public void add(long days) throws IllegalArgumentException {
        subtract(-days);
    }
    
    /**
     * diff - computes the time span measured in days between two dates.
     *
     * @param  d      a Date object. 
     * @return        the number of days between the date represented by the calling object
     *                and the date represented by the object given as argument.
     *                If the argument denotes an earlier date than the calling object, the result
     *                is positive, otherwise negative.
     */ 
    public long diff(Date d) {
        if ( d == this )
            return 0;
        else
            return daysSince1900_01_01 - d.daysSince1900_01_01;
    }
    
   
    /**
     * getYear - computes the year part of a date.
     *
     * @return        an integer denoting the year part in the date represented by the calling object.
     *                The returned value will allways be an integer >= 1900.
     */
    public int getYear() {
        return year;
    }
 
    /**
     * getMonth - computes the month part of a date.
     *
     * @return        an integer denoting the month number in the date represented by the calling object.
     *                The returned value is in the interval [1,12].
     */
    public int getMonth() {
        return month;
    }

    /**
     * getDay - computes the day part of a date.
     *
     * @return        an integer denoting the day number in the current month in the date 
     *                represented by the calling object.
     *                The returned value is in the interval [1,m], where m denotes the
     *                number of days in the current month. The calculation of m takes
     *                occasional leap days into account.
     */
    public int getDay() {
        return day;
    }
    
    /**
     * getDayNo - computes the day number part of a date.
     *
     * @return        an integer denoting the day number in the current year in the date 
     *                represented by the calling object.
     *                The returned value is in the interval [1,y], where y denotes the
     *                number of days in the current year. The calculation of y takes
     *                occasional leap days into account.
     */
    public int getDayNo() {
        return dayNo;
    }
    
    
    // Private implementation methods
    
    private DateDetails getDetails() {
        return new DateDetails();
    }
       
    private int computeDayNo() {
        monthDays[2] = isLeapYear(year) ? 29 : 28;
        int dno = day;
        for ( int m = 1; m < month; m++ )
            dno += monthDays[m];
        return dno;
    }

    private long computeDaysBeforeThisYear() {
        return computeDaysBeforeYear(year) - computeDaysBeforeYear(1900);
    }
            
    private long computeDaysBeforeYear(int year) {
        int y = year - 1;   
        return  365*y + y/4 - y/100 + y/400;
    }
    
    private void computeDetails() {
        long days = computeYear(daysSince1900_01_01);
        assert days >= 0 && days < length(year);
        dayNo = days == 0 ? length(year) : (int)days;
        days = computeMonth(days);
        assert days >= 0 && days < monthDays[month];
        day = days == 0 ? monthDays[month] : (int)days;
    }
    
    private long computeYear(long days) {
        year = 1900;
        while ( days > length(year) ) {
            days -= length(year);
            year++;
        }
        return days;
    }
    
    private long computeMonth(long days) {
        month = 1;
        monthDays[2] = isLeapYear(year) ? 29 : 28;
        while ( days > monthDays[month] ) {
            days -= monthDays[month];
            month++;
        }
        return days;
    }
        
    private int length(int year) {
        return isLeapYear(year) ? 366 : 365;
    }
    
    private boolean isLeapYear(long year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    private void checkDate(int year,int month,int day) throws IllegalStateException {
        monthDays[2] = isLeapYear(year) ? 29 : 28;
        if ( year < 1900 || month < 1 || month > 12 || day < 1 || day > monthDays[month] )
            throw new IllegalStateException("Invalid date");
    }
    
    private void checkDayNo(long day) throws IllegalArgumentException {
        if ( day < 1 )
            throw new IllegalArgumentException("Date before 1900-01-01.");
    }
}
