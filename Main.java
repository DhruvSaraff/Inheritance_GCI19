import java.util.*;

// Represent a point in the xy plane
class Point {
    private double x, y;

    // Default constructor
    Point() {
        x = y = 0.0;
    }

    // Constructor to initialize the coordinates
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Get the x coordinate
    public double getX() {
        return x;
    }

    // Get the y coordinate
    public double getY() {
        return y;
    }

    // Change the value of x
    public void setX(double x) {
        this.x = x;
    }

    //Change the value of y
    public void setY(double y) {
        this.y = y;
    }

    // Find the distance of this point with another point
    public double dist(Point p) {
        return Math.sqrt((x - p.getX()) * (x - p.getX()) + (y - p.getY()) * (y - p.getY()));
    }

    // Find the slope of this point with another point
    public double slope(Point p) {
        return (y - p.y) / (x - p.x);
    }
}

// Represent a quadrilateral in xy plane
class Quadrilateral {
    protected Point a, b, c, d;

    // DEfault constructor
    public Quadrilateral() {
        a = new Point();
        b = new Point();
        c = new Point();
        d = new Point();
    }

    // Constructor to initialize the points
    public Quadrilateral(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        a = new Point(x1, y1);
        b = new Point(x2, y2);
        c = new Point(x3, y3);
        d = new Point(x4, y4);
    }

    // Get the perimeter of the quadrilateral
    public double perimeter() {
        return a.dist(b) + b.dist(c) + c.dist(d) + d.dist(a);
    }

    // Get the length of the first diagonal
    public double diagonal1() {
        return a.dist(c);
    }

    // Get the length of the second diagonal
    public double diagonal2() {
        return b.dist(d);
    }
}

// Represent a rhombus by extending to the class Quadrilateral
class Rhombus extends Quadrilateral {

    // Default constructor
    public Rhombus() {
        // Call the default constructor of the super class
        super();
    }

    // Constructor to initialize the rhombus
    public Rhombus(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) throws Exception {
        // Call the parameterized constructor of the super class
        super(x1, y1, x2, y2, x3, y3, x4, y4);
        if (!check())
            throw new IllegalAccessException("Length of all 4 sides is not same");
    }

    // Check if length of all sides is the same
    protected boolean check() {
        return a.dist(b) == b.dist(c) && c.dist(d) == d.dist(a) && b.dist(c) == c.dist(d);
    }

    // Calculate and return the area of the rhombus
    public double area() {
        return 0.5 * diagonal1() * diagonal2();
    }
}

// Represent a square by extending to the class Quadrilateral
class Square extends Rhombus {

    // Default constructor
    public Square() {
        super();
    }

    // Constructor to initialize the square
    public Square(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) throws Exception {
        // Call the parameterized constructor of the super class
        super(x1, y1, x2, y2, x3, y3, x4, y4);
        if (!check())
            throw new IllegalAccessException("Length of all 4 sides is not same");
    }

    // Check if the points form a square
    // The function is an example of run-time polymorphism. All objects of this class type will invoke this function
    protected boolean check() {
        return super.check() && a.slope(b) * b.slope(c) == -1;
    }

    // Calculate the length of each diagonal
    private double diagonal() {
        return a.dist(c);
    }

    // Return length of first diagonal
    public double diagonal1() {
        return diagonal();
    }

    // Return length of second diagonal
    public double diagonal2() {
        return diagonal();
    }

    // Calculate and return the area of the square
    // The function is an example of run-time polymorphism. All objects of this class type will invoke this function
    public double area() {
        return a.dist(b) * a.dist(b);
    }
}

public class Main {

    // Create the objects of the above functions and call their methods
    public static void main(String args[]) throws Exception {

        double x1, x2, x3, x4, y1, y2, y3, y4;

        Scanner sc = new Scanner(System.in);

        // Create a quadrilateral
        System.out.println("Enter the coordinates of any quadrilateral");
        x1 = sc.nextDouble();
        y1 = sc.nextDouble();
        x2 = sc.nextDouble();
        y2 = sc.nextDouble();
        x3 = sc.nextDouble();
        y3 = sc.nextDouble();
        x4 = sc.nextDouble();
        y4 = sc.nextDouble();

        Quadrilateral q = new Quadrilateral(x1, y1, x2, y2, x3, y3, x4, y4);
        System.out.println("Diagonal 1 : " + q.diagonal1());
        System.out.println("Diagonal 2 : " + q.diagonal2());
        System.out.println("Perimeter : " + q.perimeter());

        // Create a rhombus
        System.out.println("Enter the coordinates of any rhombus");
        x1 = sc.nextDouble();
        y1 = sc.nextDouble();
        x2 = sc.nextDouble();
        y2 = sc.nextDouble();
        x3 = sc.nextDouble();
        y3 = sc.nextDouble();
        x4 = sc.nextDouble();
        y4 = sc.nextDouble();

        Rhombus r = new Rhombus(x1, y1, x2, y2, x3, y3, x4, y4); // We must declare r as Rhombus otherwise static binding fails
        System.out.println("Diagonal 1 : " + r.diagonal1());
        System.out.println("Diagonal 2 : " + r.diagonal2());
        System.out.println("Perimeter : " + r.perimeter());
        System.out.println("Area : " + r.area());

        // Create a square
        System.out.println("Enter the coordinates of any square");
        x1 = sc.nextDouble();
        y1 = sc.nextDouble();
        x2 = sc.nextDouble();
        y2 = sc.nextDouble();
        x3 = sc.nextDouble();
        y3 = sc.nextDouble();
        x4 = sc.nextDouble();
        y4 = sc.nextDouble();

        Rhombus s = new Square(x1, y1, x2, y2, x3, y3, x4, y4); // This time all functions will be called on Square during run-time
        System.out.println("Diagonal 1 : " + s.diagonal1());
        System.out.println("Diagonal 2 : " + s.diagonal2());
        System.out.println("Perimeter : " + s.perimeter());
        System.out.println("Area : " + s.area());
    }
}
