public class SquareIntList {

    private static class IntList {
        public int first;
        public IntList next;

        public IntList(int f, IntList r) {
            first = f;
            next = r;
        }

        /** Adds a new IntList to the end of the list */
        public void add(int x) {
            this.squareList();
            IntList p = this;
            while (p.next != null) {
                p = p.next;
            }
            p.next = new IntList(x, null);
        }

        /** Squares the current list */
        private void squareList() {
            IntList p1 = this;
            IntList p2 = this.next;

            while (p2 != null) {
                insertSquare(p1, p2);
                p1 = p2;
                p2 = p2.next;
            }
            p1.next = new IntList(p1.first * p1.first, null);
        }

        /** Inserts the squared IntList and points it to the next element */
        private void insertSquare(IntList p1, IntList p2) {
            p1.next = new IntList(p1.first * p1.first, p2);
        }
    }

    public static void main(String[] args) {
        IntList L = new IntList(2, null);
        L = new IntList(1, L);
        L.add(5);
        L.add(7);
    }
}