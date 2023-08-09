public class Body {
    private static final double G = 6.67e-11;
    public double xxPos; // Its current x position
    public double yyPos; // Its current y position
    public double xxVel; // Its current velocity in the x direction
    public double yyVel; // Its current velocity in the y direction
    public double mass; //Its mass
    // The name of the file that corresponds to the image that depicts the body (for example, jupiter.gif)
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
        xxPos  = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos  = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt((this.xxPos - b.xxPos) * (this.xxPos - b.xxPos) +
                (this.yyPos - b.yyPos) * (this.yyPos - b.yyPos));
    }

    public double calcForceExertedBy(Body b) {
        return (G * this.mass * b.mass) / (calcDistance(b) * calcDistance(b));
    }

    public double calcForceExertedByX(Body b) {
        double result = calcForceExertedBy(b) * (this.xxPos - b.xxPos) / calcDistance(b);
        //return result > 0 ? result : -result;
        return -result;
    }

    public double calcForceExertedByY(Body b) {
        double result = calcForceExertedBy(b) * (this.yyPos - b.yyPos) / calcDistance(b);
        //return result > 0 ? result : -result;
        return -result;
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double result = 0;
        for (Body body : bodies) {
            if (this.equals(body))
                continue;
            result += calcForceExertedByX(body);
        }
        return result;
    }
    public double calcNetForceExertedByY(Body[] bodies) {
        double result = 0;
        for (Body body : bodies) {
            if (this.equals(body))
                continue;
            result += calcForceExertedByY(body);
        }
        return result;
    }

    public void update(double dt, double fX, double fY) {
        this.xxVel += (fX * dt) / this.mass;
        this.yyVel += (fY * dt) / this.mass;
        this.xxPos += xxVel * dt;
        this.yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(xxPos, yyPos, "images/" + this.imgFileName);
        StdDraw.show();
    }























}
