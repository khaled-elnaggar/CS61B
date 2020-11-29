public class NBody {

    public static double readRadius(String fileName) {
        if (fileName == null) {
            return -1.0;
        }
        In file = new In(fileName);
        file.readInt();
        return file.readDouble();
    }

    public static Body[] readBodies(String fileName) {
        if (fileName == null || fileName.equals("null")) {
            return null;
        }

        In file = new In(fileName);
        int numberOfPlanets = file.readInt();
        file.readDouble();

        Body[] bodies = new Body[numberOfPlanets];

        for (int i = 0; i < numberOfPlanets; i++) {

            double xP = file.readDouble();
            double yP = file.readDouble();
            double xV = file.readDouble();
            double yV = file.readDouble();
            double m = file.readDouble();
            String img = file.readString();
            Body body = new Body(xP, yP, xV, yV, m, img);
            bodies[i] = body;
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String fileName = args[2];
        double radius = NBody.readRadius(fileName);
        Body[] bodies = NBody.readBodies(fileName);
        int numberOfPlanets = bodies.length;

        String bkGroundImg = "./images/starfield.jpg";

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        StdDraw.picture(0, 75, bkGroundImg);
        for (Body b: bodies) {
            StdDraw.picture(b.xxPos, b.yyPos, "./images/" + b.imgFileName);
        }
        StdDraw.show();

        for (double timePassed = 0.0; timePassed <= T; timePassed += dt) {
            double[] xForces = new double[numberOfPlanets];
            double[] yForces = new double[numberOfPlanets];
            
            //calculate forces on each planet
            for (int i = 0; i < numberOfPlanets; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            //update every planet's position
            for (int i = 0; i < numberOfPlanets; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            //draw every planet
            StdDraw.picture(0, 75, bkGroundImg);
            for (Body b: bodies) {
                StdDraw.picture(b.xxPos, b.yyPos, "./images/" + b.imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        
StdOut.printf("%d\n", bodies.length);
StdOut.printf("%.2e\n", radius);
for (int i = 0; i < bodies.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
}
    }
}























