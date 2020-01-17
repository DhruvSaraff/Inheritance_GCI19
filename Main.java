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

    // Default constructor
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

    // Display all the properties of quadrilaterals
    public void properties() {
        System.out.println("It has 4 sides and 4 vertices");
        System.out.println("The sum of the interior angles is 360 degrees");
    }
}

// Represent a parallelogram in the xy plane by extending to Quadrilateral
class Parallelogram extends Quadrilateral {

    // Since all parallelograms are quadrilaterals, we inherit all properties of that class
    // These properties include the points, perimeter, diagonals, and properties

    // Default Constructor
    public Parallelogram() {
        // We call the constructor of the super class
        super();
    }

    // Constructor to initialize the parallelogram
    public Parallelogram(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) throws Exception {
        // Call the parameterized constructor of the super class
        super(x1, y1, x2, y2, x3, y3, x4, y4);
        if (!check())
            throw new IllegalAccessException("The coordinates do not form a quadrilateral");
    }

    // Check if the quadrilateral is actually a parallelogram
    // We declare it as protected since the derived classes will inherit it
    protected boolean check() {
        return a.slope(b) == d.slope(c) && b.slope(c) == a.slope(d);
    }

    // Display all the properties of parallelograms
    // This overrides the properties method in the base class and is an example of run-time polymorphism.
    // All objects of this class type will invoke this method due to dynamic binding during run-time
    public void properties() {
        // We call the properties of the super class as a parallelogram has all properties of a quadrilateral
        super.properties();
        // We now display the additional properties
        System.out.println("Both pairs of opposite sides are equal and parallel");
        System.out.println("Both pairs of opposite angles are equal");
        System.out.println("The diagonals bisect each other");
    }

    // The perimeter and diagonal functions need not be implemented as it has been inherited
}

// Represent a rhombus by extending to the class Parallelogram
class Rhombus extends Parallelogram {

    // Since all rhombus are parallelograms, we inherit all properties of that class
    // These properties include the points, perimeter, diagonals, and properties, some of which in turn are inherited from Quadrilateral

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
    // We use the function implemented in the base class as well
    // This is an example of run-time polymorphism and method overriding
    protected boolean check() {
        return super.check() && a.dist(b) == b.dist(c) && c.dist(d) == d.dist(a) && b.dist(c) == c.dist(d);
    }

    // Calculate the area of the rhombus
    public double area() {
        return 0.5 * diagonal1() * diagonal2();
    }

    // Display all the properties of rhombus
    // This overrides the properties method in the base class and is an example of run-time polymorphism.
    // All objects of this class type will invoke this method due to dynamic binding during run-time
    public void properties() {
        // We call the properties of the super class as a rhombus has all properties of a parallelogram
        super.properties();
        // We now display the additional properties
        System.out.println("All sides are equal in length");
        System.out.println("The diagonals intersect at right angles and bisect the vertices");
    }

    // All functions already implemented in the base class need not be rewritten
}

// Represent a square by extending to the class Quadrilateral
class Square extends Rhombus {

    // Since all squares are rhombus, we inherit all properties of that class
    // These properties include the points, perimeter, diagonals, area, and properties, some of which in turn are inherited from Quadrilateral


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
    // It uses the check function of the base class as well
    // The function is an example of run-time polymorphism. All objects of this class type will invoke this function
    protected boolean check() {
        return super.check() && a.slope(b) * b.slope(c) == -1;
    }

    // Calculate the length of each diagonal
    public double diagonal() {
        return diagonal();
    }

    // Display all the properties of squares
    // This overrides the properties method in the base class and is an example of run-time polymorphism.
    // All objects of this class type will invoke this method due to dynamic binding during run-time
    public void properties() {
        // We call the properties of the super class as a square has all properties of a rhombus
        super.properties();
        // We now display the additional properties
        System.out.println("Both the diagonals are equal in length");
        System.out.println("Each of the interior angles is 90 degrees");
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
        // We cannot call area as it is not implemented for Quadrilateral
        q.properties();

        // Create a parallelogram
        System.out.println("Enter the coordinates of any parallelogram");
        x1 = sc.nextDouble();
        y1 = sc.nextDouble();
        x2 = sc.nextDouble();
        y2 = sc.nextDouble();
        x3 = sc.nextDouble();
        y3 = sc.nextDouble();
        x4 = sc.nextDouble();
        y4 = sc.nextDouble();

        Quadrilateral p = new Parallelogram(x1, y1, x2, y2, x3, y3, x4, y4); // All functions will be called on Parallelogram during run-time due to dynamic binding
        System.out.println("Diagonal 1 : " + p.diagonal1());
        System.out.println("Diagonal 2 : " + p.diagonal2());
        System.out.println("Perimeter : " + p.perimeter());
        p.properties();

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

        Rhombus r = new Rhombus(x1, y1, x2, y2, x3, y3, x4, y4); // We cannot declare it as Parallelogram r as static binding shall fail
        System.out.println("Diagonal 1 : " + r.diagonal1());
        System.out.println("Diagonal 2 : " + r.diagonal2());
        System.out.println("Perimeter : " + r.perimeter());
        System.out.println("Area : " + r.area());
        r.properties();

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

        Rhombus s = new Square(x1, y1, x2, y2, x3, y3, x4, y4); // All functions will be called on Square during run-time
        System.out.println("Diagonal 1 : " + s.diagonal1());
        System.out.println("Diagonal 2 : " + s.diagonal2());
        System.out.println("Perimeter : " + s.perimeter());
        System.out.println("Area : " + s.area());
        s.properties();
    }
}
