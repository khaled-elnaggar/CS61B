public class CharUt{
    public static int countChar(String a, char c) {
        int counter = 0;
        for(int i = 0; i < a.length(); i++){
            if(a.charAt(i) == c){
                counter += 1;
            }
        }
        return counter;
    }
}
