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
}