class TestBody {
    public static void main(String[] args) {
        Body samh = new Body(1, 0, 0, 0, 7e5, "");
        Body aegir = new Body(3, 3, 0, 0, 8e5, "");
        Body roc = new Body(5, -3, 0, 0, 9e6, "");
        Body[] bodies = {
            samh,
            aegir,
            roc
        };

        if (samh.calcNetForceExertedByX(bodies) - 15.04 < 0.01) {
            System.out.println("Pass Xforces Test");
        } else {
            System.out.println("Failed Xforces Test");
        }

        if (samh.calcNetForceExertedByY(bodies) - 7.69 < 0.01) {
            System.out.println("Pass Yforces Test");
        } else {
            System.out.println("Failed Yforces Test");
        }
    }
}
