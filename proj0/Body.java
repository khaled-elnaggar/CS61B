public class Body {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String img) {
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = xxPos - b.xxPos;
        double dy = yyPos - b.yyPos;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double calcForceExertedBy(Body b) {
        if (this.equals(b)) {
            return 0;
        }
        double rSquare = Math.pow(this.calcDistance(b), 2);
        final double G = 6.67e-11;
        return G * mass * b.mass / rSquare;
    }

    public double calcForceExertedByX(Body b) {
        double dx = b.xxPos - xxPos;
        return calcForceExertedBy(b) * dx / calcDistance(b);
    }

    public double calcForceExertedByY(Body b) {
        double dy = b.yyPos - yyPos;
        return calcForceExertedBy(b) * dy / calcDistance(b);
    }


    public double calcNetForceExertedByX(Body[] bodies) {
        double sumX = 0;
        for (Body b: bodies) {
            if (this.equals(b)) {
                continue;
            }
            sumX += calcForceExertedByX(b);
        }
        return sumX;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double sumY = 0;
        for (Body b: bodies) {
            if (this.equals(b)) {
                continue;
            }
            sumY += calcForceExertedByY(b);
        }
        return sumY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel = xxVel + dt * aX;
        yyVel = yyVel + dt * aY;
        xxPos = xxPos + xxVel * dt;
        yyPos = yyPos + yyVel * dt;
    }





}