public class Student {
    public String name;
    public int SID;
    public Student(String n, int id) {
        name = n;
        SID = id;
    }
    public boolean equals(Student other) {
        System.out.print(" Inside student ");
        if (name.equals(other.name) &&
            SID == other.SID) {
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        Student sB = new Student("Borf", 123);
        Student sG = new Student("Gorf", 123);
        Student sB2 = new Student("Borf", 123);
        Object oG = sG;
        Object oB2 = sB2;
        System.out.println("1 " + sB.equals(sG)); //Student
        System.out.println("2 " + sB.equals(sB2)); //Student
        System.out.println("3 " + (sB == sB2));
        System.out.println("4 " + sG.equals(oG)); //Object
        System.out.println("5 " + (sG == oG)); 
        System.out.println("6 " + sG.equals((Student) oG)); //Student
        System.out.println("7 " + sB.equals(oB2)); //Object
        System.out.println("8 " + (sB == oB2)); 
        System.out.println("9 " + sB.equals((Student) oB2)); //Student
        System.out.println("10 " + ((Object) sB2).equals((Student) oB2));
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
