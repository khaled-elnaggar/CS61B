public class AddAdjacent {
    private static class IntList {
        public int first;
        public IntList next;

        public IntList(int f, IntList r) {
            first = f;
            next = r;
        }


        public void addAdjacent() {
            if (this.next == null) {
                return;
            }

            IntList p1 = this;
            IntList p2 = this.next;

            while (p2 != null) {
                if (p1.first == p2.first) {
                    p1.first *= 2;
                    p1.next = p2.next;
                    p2.next = null;
                    p2 = p1.next;
                } else {
                    p1 = p2;
                    p2 = p2.next;
                }
            }
        }
    }
    public static void main(String[] args) {
        IntList L = new IntList(16, null);
        L = new IntList(8, L);
        L = new IntList(4, L);
        L = new IntList(2, L);
        L = new IntList(2, L);
        L.addAdjacent();
    }
}