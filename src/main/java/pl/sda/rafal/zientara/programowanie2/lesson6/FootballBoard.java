package pl.sda.rafal.zientara.programowanie2.lesson6;

import java.util.ArrayList;
import java.util.List;

public class FootballBoard {
    public final int width;
    public final int height;
    private List<Line> lines;

    public FootballBoard(int width, int height) {
        this.width = width;
        this.height = height;
        lines = new ArrayList<>();
    }

    public void addLine(Line line) {
        if (!lineExists(line)) {
            lines.add(line);
        } else {
            throw new IllegalStateException("Line exist!");
        }
    }

    public boolean lineExists(Line line){
        return lineExists(line.start,line.end);
    }

    public boolean lineExists(Point start, Point end) {
        for (Line line : lines){
            if (line.start.equals(start) && line.end.equals(end)){
                return true;
            }
            if (line.start.equals(end) && line.end.equals(start)){
                return true;
            }
        }
        return false;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void initSides() {
        initLeftSide();
        initRightSide();
        initTopGoal();
        initBottomGoal();
        initTopSide();
        initBottomSide();
    }

    private void initBottomSide() {
        int widthCenter = width/2;
        for (int i = 0; i < width; i++) {
            if (i!=widthCenter && i!=widthCenter-1) {
                int y = height-1;
                int x1 = i;
                int x2 = i + 1;
                Point start = new Point(x1, y);
                Point end = new Point(x2, y);
                Line line = new Line(start, end, LineType.SIDE);
                addLine(line);
            }
        }
    }

    private void initTopSide() {
        int widthCenter = width/2;
        for (int i = 0; i < width; i++) {
            if (i!=widthCenter && i!=widthCenter-1) {
                int y = 1;
                int x1 = i;
                int x2 = i + 1;
                Point start = new Point(x1, y);
                Point end = new Point(x2, y);
                Line line = new Line(start, end, LineType.SIDE);
                addLine(line);
            }
        }
    }

    private void initTopGoal() {
        int widthCenter = width/2;
        Line line1 = new Line(new Point(widthCenter - 1, 0), new Point(widthCenter, 0), LineType.SIDE);
        Line line2 = new Line(new Point(widthCenter, 0), new Point(widthCenter+1, 0), LineType.SIDE);
        Line line3 = new Line(new Point(widthCenter-1, 0), new Point(widthCenter-1, 1), LineType.SIDE);
        Line line4 = new Line(new Point(widthCenter+1, 0), new Point(widthCenter+1, 1), LineType.SIDE);
        addLine(line1);
        addLine(line2);
        addLine(line3);
        addLine(line4);
    }

    private void initBottomGoal() {
        int widthCenter = width/2;
        Line line1 = new Line(new Point(widthCenter - 1, height), new Point(widthCenter, height), LineType.SIDE);
        Line line2 = new Line(new Point(widthCenter, height), new Point(widthCenter+1, height), LineType.SIDE);
        Line line3 = new Line(new Point(widthCenter-1, height), new Point(widthCenter-1, height-1), LineType.SIDE);
        Line line4 = new Line(new Point(widthCenter+1, height), new Point(widthCenter+1, height-1), LineType.SIDE);
        addLine(line1);
        addLine(line2);
        addLine(line3);
        addLine(line4);
    }

    private void initLeftSide() {
        for (int i = 1; i < height-1; i++) {
            int x = 0;
            int y1 = i;
            int y2 = i+1;
            Point start = new Point(x,y1);
            Point end = new Point(x,y2);
            Line line = new Line(start,end,LineType.SIDE);
            addLine(line);
        }
    }

    private void initRightSide() {
        for (int i = 1; i < height-1; i++) {
            int x = width;
            int y1 = i;
            int y2 = i+1;
            Point start = new Point(x,y1);
            Point end = new Point(x,y2);
            Line line = new Line(start,end,LineType.SIDE);
            addLine(line);
        }
    }

    public boolean hasAnyConnection(Point position) {
        for(Line line : lines){
            if (line.start.equals(position) || line.end.equals(position)){
                return true;
            }
        }
        return false;
    }
}
