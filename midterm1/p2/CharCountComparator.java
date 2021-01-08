public class CharCountComparator implements Comparator<String>{
    char comparisonChar;
    public CharCountComparator(char givenC) {
        comparisonChar = givenC;
    }
    public int compare(String a, String b){
        return CharUt.countChar(a, comparisonChar) - CharUt.countChar(b, comparisonChar);
    }
    
    // public static void main(String []args){
    //     CharCountComparator xComp = new CharCountComparator('x'); 
    //     System.out.println(xComp.compare("exxxxon", "sxoxmxe axons"));
    //     CharCountComparator zComp = new CharCountComparator('z'); 
    //     System.out.println(zComp.compare("mazzy", "lazer"));
    // }
}
