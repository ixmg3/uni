// Interface segregation
interface Shape {
    double calculateArea();
}

// Open/Closed Principle - Shape class is open for extension but closed for modification
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle implements Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double calculateArea() {
        return length * width;
    }
}

// Dependency Inversion
class AreaCalculator {
    public double calculateShapeArea(Shape shape) {
        return shape.calculateArea();
    }
}

// Liskov Substitution Principle - Square is a subclass of Rectangle
class Square extends Rectangle {
    public Square(double side) {
        super(side, side);
    }
}

public class Main {

    public static void main(String[] args) {
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);
        Square square = new Square(4);

        AreaCalculator calculator = new AreaCalculator();

        double circleArea = calculator.calculateShapeArea(circle);
        double rectangleArea = calculator.calculateShapeArea(rectangle);
        double squareArea = calculator.calculateShapeArea(square);

        System.out.println("Circle Area: " + circleArea);
        System.out.println("Rectangle Area: " + rectangleArea);
        System.out.println("Square Area: " + squareArea);
    }
}