public interface NullSafeStringComparator{
    /**
        Returns a negative if String1 < String 2
        0 if equals
        and a positive otherwise
        Null is considered less than anything
        return 0 if both are null
     */
    
    public int compare(String s1, String s2);
}