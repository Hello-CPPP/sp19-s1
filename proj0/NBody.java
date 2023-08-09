import javax.sound.midi.Soundbank;
import javax.xml.stream.events.StartDocument;

public class NBody {
    public static double readRadius(String fileName) {
        In reader = new In(fileName);
        reader.readInt();
        return reader.readDouble();
    }

    public static Body[] readBodies(String fileName) {
        In reader = new In(fileName);
        int numberOfBody = reader.readInt();
        double radius = reader.readDouble();

        Body[] bodies = new Body[numberOfBody];
        for (int i = 0; i < numberOfBody; i++) {
            bodies[i] = new Body(reader.readDouble(), reader.readDouble(), reader.readDouble(),
                    reader.readDouble(), reader.readDouble(), reader.readString());
        }

        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");


        double[] xForces = new double[bodies.length];
        double[] yForces = new double[bodies.length];


        //double timeStep = 100000;
        for (double time = 0; time < T; time+=dt) {
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for (int i = 0; i < bodies.length; i++)
                bodies[i].update(dt, xForces[i], yForces[i]);

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Body body : bodies)
                body.draw();

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
