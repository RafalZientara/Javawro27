package programowanie2.lesson6;

import java.util.*;

public class FootballBoard {
    public final int width;
    public final int height;
    private List<Line> lines;

    public FootballBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.lines = new ArrayList<>();
    }


    public void addLine(Line line) {
        if (!lineExist(line)) {
            lines.add(line);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void initSides() {
        initLeftSide();
        initRightSide();
        initTopGoal();
        initBottomGoal();
        initTopSides();
        initBottomSides();
    }

    private void initBottomSides() {
        for (int i = 0; i < width/2-1; i++) {
            Line line1 = new Line(new Point(i, height-1), new Point(i+1, height-1), LineType.SIDE);
            addLine(line1);
        }

        for (int i = width/2+1; i < width; i++) {
            Line line1 = new Line(new Point(i, height-1), new Point(i+1, height-1), LineType.SIDE);
            addLine(line1);
        }

    }

    private void initTopSides() {
        for (int i = 0; i < width/2-1; i++) {
            Line line1 = new Line(new Point(i, 1), new Point(i+1,1), LineType.SIDE);
            addLine(line1);
        }

        for (int i = width/2+1; i < width; i++) {
            Line line1 = new Line(new Point(i, 1), new Point(i+1,1), LineType.SIDE);
            addLine(line1);
        }

    }

    private void initTopGoal() {
        int widthCenter = width/2;
        Line line1 = new Line(new Point(widthCenter-1, 0), // top left
                 new Point(widthCenter, 0), LineType.SIDE);

        Line line2 = new Line(new Point(widthCenter, 0),   // top right
                new Point(widthCenter+1, 0), LineType.SIDE);

        Line line3 = new Line(new Point(widthCenter-1, 0), // left
                new Point(widthCenter-1, 1), LineType.SIDE);

        Line line4 = new Line(new Point(widthCenter+1, 0), // right
                new Point(widthCenter+1, 1), LineType.SIDE);

        addLine(line1);
        addLine(line2);
        addLine(line3);
        addLine(line4);
    }

    private void initBottomGoal() {
        int widthCenter = width/2;
        Line line1 = new Line(new Point (widthCenter-1,height),new Point(widthCenter,height),LineType.SIDE);
        Line line2 = new Line(new Point (widthCenter,height),new Point(widthCenter+1,height),LineType.SIDE);
        Line line3 = new Line(new Point (widthCenter-1,height),new Point(widthCenter-1,height-1),LineType.SIDE);
        Line line4 = new Line(new Point (widthCenter+1,height),new Point(widthCenter+1,height-1),LineType.SIDE);
        addLine(line1);
        addLine(line2);
        addLine(line3);
        addLine(line4);

    }

    private void initLeftSide() {
        for (int i = 1; i < height - 1; i++) {
            int x = 0;
            int y1 = i;
            int y2 = i + 1;
            Point start = new Point(x, y1);
            Point end = new Point(x, y2);
            Line line = new Line(start, end, LineType.SIDE);
            addLine(line);
        }
    }

    private void initRightSide() {
        for (int i = 1; i < height - 1; i++) {
            int x = width;
            int y1 = i;
            int y2 = i + 1;
            Point start = new Point(x, y1);
            Point end = new Point(x, y2);
            Line line = new Line(start, end, LineType.SIDE);
            addLine(line);
        }
    }

    public boolean lineExist(Line line) {
        return lineExist(line.start, line.end);
    }

    public boolean lineExist(Point start, Point end) {
        for (Line line : lines) {
            if (line.start.equals(start) && line.end.equals(end))
                return true;

            if (line.start.equals(end) && line.end.equals(start))
                return true;
        }
        return false;
    }

    public List<Line> getLines() {
        return lines;
    }

    public boolean hasAnyConnections(Point newPosition) {
        for(Line line: lines){
            if(line.start.equals(newPosition) || line.end.equals(newPosition)){
                return true;
            }
        }
        return false;
    }
}
