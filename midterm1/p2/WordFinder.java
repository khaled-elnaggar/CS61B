public class WordFinder {
    public static String findMax(String[] strings, Comparator<String> cmp){
        String maxStr = strings[0];
        for(String s : strings){
          if(cmp.compare(maxStr, s) < 0){
            maxStr = s;
          }
        }
        return maxStr;
    }
    public static void main(String[] args){
        String[] words = new String[] {"mazy", "mazzy", "mazozq", "kito", "zmazza", "baby", "oh no"};
        String wordWithMostZs = findMax(words, new CharCountComparator('z'));
        System.out.println(wordWithMostZs);
    }
}
