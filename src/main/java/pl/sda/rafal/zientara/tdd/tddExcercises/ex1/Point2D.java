package pl.sda.rafal.zientara.tdd.tddExcercises.ex1;

public class Point2D {

    private double x;
    private double y;

    public Point2D() {
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public double getDistanceFromCenter(){
        return Math.sqrt((x*x)+(y*y));
    }

    public double getDistanceFromPoint2D(Point2D point2D){
        return  Math.sqrt(((point2D.getX()-x)*(point2D.getX()-x))+((point2D.getY()-y)*(point2D.getY()-y)));
    }

    public void move(double deltaX, double deltaY){
        setX(deltaX);
        setY(deltaY);
    }
}
