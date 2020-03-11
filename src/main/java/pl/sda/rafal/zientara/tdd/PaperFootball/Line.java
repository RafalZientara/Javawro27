package pl.sda.rafal.zientara.tdd.PaperFootball;

public class Line {

    final Point startPoint;
    final Point endPoint;
    final LineType type;

    public Line(Point startPoint, Point endPoint, LineType type) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.type = type;
        int xDiff = Math.abs(startPoint.x-endPoint.x);
        int yDiff = Math.abs(startPoint.y-endPoint.y);
        if (xDiff>1 || yDiff>1) {
            throw new IllegalArgumentException();
        }
    }

    public Line(int x1, int y1, int x2, int y2, LineType type) {
        this.type = type;
        startPoint=new Point(x1,y1);
        endPoint = new Point(x2,y2);
        int xDiff = Math.abs(x1-x2);
        int yDiff = Math.abs(y1-y2);
        if (xDiff>1 || yDiff>1) {
            throw new IllegalArgumentException();
        }
    }
}
