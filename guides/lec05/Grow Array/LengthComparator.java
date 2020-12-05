public class LengthComparator implements NullSafeStringComparator{
    @Override
    public int compare(String a, String b){
        if(a==null && b==null){
            return 0;
        }

        if(a==null){
            return -1;
        }

        if(b == null){
            return 1;
        }
        
        return a.length() - b.length();
    }

/*     public static void main(String[] args){
        LengthComparator lc = new LengthComparator();
        System.out.println(lc.compare(null, "5") < 0);
        System.out.println(lc.compare("5", null) > 0);
        System.out.println(lc.compare(null, null) == 0);
        System.out.println(lc.compare("123", "123") == 0);
        System.out.println(lc.compare("123", "12") > 0);
        System.out.println(lc.compare("12", "123") < 0);
    } 
*/

}