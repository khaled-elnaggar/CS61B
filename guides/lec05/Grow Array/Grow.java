public class Grow{
    public static String max(String[] a, NullSafeStringComparator sc){
        String maxStr = a[0];
        for(int i = 1; i < a.length; i++){
            if(sc.compare(maxStr, a[i]) < 0){
                maxStr = a[i];
            }
        }
        return maxStr;
    }

    public static String[][] step(String[][] arr){
        int height = arr.length;
        int width = arr[0].length;

        String[][] stepped = new String[height][width];
        for(int i = 1; i < height - 1; i++){
            for(int j = 1; j < width - 1; j++){
                String[] temp = new String[9];
                int count = 0;
                for(int k = -1; k <= 1; k++){
                    for(int m = -1; m <= 1; m++){
                        temp[count] = arr[i + k][j + m];
                        count++;

                    }
                }
                stepped[i][j] = max(temp, new LengthComparator());
            }
        }
        return stepped;
    }

    public static void main(String[] args){
        Grow g = new Grow();
/*         
        LengthComparator lc = new LengthComparator();
        System.out.println(g.max(new String[] {null, "5"}, lc).equals("5"));
        System.out.println(g.max(new String[] {"5", null}, lc).equals("5"));
        System.out.println(g.max(new String[] {"123", "123"}, lc).equals("123"));
        System.out.println(g.max(new String[] {"abc", "ab"}, lc).equals("abc"));
        System.out.println(g.max(new String[] {"de", "def"}, lc).equals("def"));
    
 */
        String[][] arr = new String[][] {{null, null, null, null, null, null},
                                        {null, "a", "cat", "cat", "dogs", null},
                                        {null, "a", null, "cat", "a", null},
                                        {null, "a", "ca", "", "ca", null}, 
                                        {null, null, null, null, null, null}};

        String[][] newArr = step(arr);
        for(int i = 0; i < newArr.length; i++){
            for(int j = 0; j < newArr[0].length; j++){
                System.out.printf("%5s, ", newArr[i][j]);
            }
            System.out.println();        
        }

    }
}
