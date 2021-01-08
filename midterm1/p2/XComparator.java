public class XComparator implements Comparator<String>{
    public int compare(String a, String b){
        return CharUt.countChar(a, 'x') - CharUt.countChar(b, 'x');
    }

}
