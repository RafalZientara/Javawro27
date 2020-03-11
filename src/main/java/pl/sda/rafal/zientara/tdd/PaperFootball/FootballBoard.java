package pl.sda.rafal.zientara.tdd.PaperFootball;


import java.util.ArrayList;
import java.util.List;

public class FootballBoard {
    public final int width;
    public final int height;
    public List<Line> lines;

    public FootballBoard(int width, int height) {
        this.width = width;
        this.height = height;
        lines = new ArrayList<>();
    }

    public void addLine(Line line) {
        if (!lineExists(line.startPoint, line.endPoint))
            lines.add(line);
        else {
            throw new IllegalArgumentException();
        }
    }


    public boolean lineExists(Point startPoint, Point endPoint) {
        for (Line line : lines) {
            if (line.startPoint.equals(startPoint) && line.endPoint.equals(endPoint)) return true;
            if (line.endPoint.equals(startPoint) && line.startPoint.equals(endPoint)) return true;
        }
        return false;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void initSides() {
        initBlockedLines();
        leftSide();
        rightSide();
        initTopGoal();
        initBottomGoal();
        initBottomSide();
        initTopSide();
    }

    private void initBlockedLines() {
        initBlockedLeftCrossedLines();
        initBlockedTopCrossedLines();
        initBlockedBottomCrossedLines();
        initVerticalBlockedLines();
        initHorizontalBlockedLines();
    }

    private void initTopSide() {
        for (int i = 0; i <width; i++) {
            if (i!=width/2-1 && i!=width/2) {
                int x1 = i;
                int x2 = i+1;
                int y = 1;
                Point start = new Point(x1, y);
                Point end = new Point(x2, y);
                Line line = new Line(start, end, LineType.SIDE);
                addLine(line);
            }
        }
    }

    private void initBottomSide() {
        for (int i = 0; i <width; i++) {
            if (i!=width/2-1 && i!=width/2) {
                int x1 = i;
                int x2 = i+1;
                int y = height-1;
                Point start = new Point(x1, y);
                Point end = new Point(x2, y);
                Line line = new Line(start, end, LineType.SIDE);
                addLine(line);
            }
        }
    }

    private void initBlockedTopCrossedLines() {
        for (int i = 0; i <width; i++) {
            if (i!=width/2-1 && i !=width/2){
                Point forwardCrossStart= new Point(i,1);
                Point forwardCrossEnd = new Point(i+1,0);
                Point BackwardCrossStart = new Point(i,0);
                Point BackwardCrossEnd = new Point(i+1,1);
                Line forwardCross = new Line(forwardCrossStart,forwardCrossEnd,LineType.BLOCKED);
                Line backwardCross = new Line(BackwardCrossStart,BackwardCrossEnd,LineType.BLOCKED);
                addLine(forwardCross);
                addLine(backwardCross);
            }
        }
    }

    private void initBlockedLeftCrossedLines() {
        for (int i = 1; i <height-1; i++) {
                Point forwardCrossStart= new Point(-1,i);
                Point forwardCrossEnd = new Point(0,i+1);
                Point BackwardCrossStart = new Point(0,i);
                Point BackwardCrossEnd = new Point(-1,i+1);
                Line forwardCross = new Line(forwardCrossStart,forwardCrossEnd,LineType.BLOCKED);
                Line backwardCross = new Line(BackwardCrossStart,BackwardCrossEnd,LineType.BLOCKED);
                addLine(forwardCross);
                addLine(backwardCross);
        }
    }

    private void initBlockedBottomCrossedLines() {
        for (int i = 0; i < width; i++) {
            if (i != width / 2 - 1 && i != width / 2) {
                Point forwardCrossStart = new Point(i, height);
                Point forwardCrossEnd = new Point(i + 1, height - 1);
                Point BackwardCrossStart = new Point(i, height - 1);
                Point BackwardCrossEnd = new Point(i + 1, height);
                Line forwardCross = new Line(forwardCrossStart, forwardCrossEnd, LineType.BLOCKED);
                Line backwardCross = new Line(BackwardCrossStart, BackwardCrossEnd, LineType.BLOCKED);
                addLine(forwardCross);
                addLine(backwardCross);
            }
        }
    }
        private void initVerticalBlockedLines() {
            for (int i = 0; i <= width; i++) {
                if (i<width/2-1 || i> width/2+1) {
                    Point startTopPoint = new Point(i,0);
                    Point endTopPoint = new Point(i,1);
                    Point startBottomPoint = new Point(i,height);
                    Point endBottomPoint = new Point(i,height-1);
                    Line topLine = new Line(startTopPoint,endTopPoint,LineType.BLOCKED);
                    Line bottomLine = new Line(startBottomPoint,endBottomPoint,LineType.BLOCKED);
                    addLine(topLine);
                    addLine(bottomLine);
                }
            }
        }

    private void initHorizontalBlockedLines() {
        for (int i =1; i < height; i++) {
            Point startTopPoint = new Point(-1, i);
            Point endTopPoint = new Point(0, i);
            Point startBottomPoint = new Point(width, i);
            Point endBottomPoint = new Point(width +1, i);
            Line topLine = new Line(startTopPoint, endTopPoint, LineType.BLOCKED);
            Line bottomLine = new Line(startBottomPoint, endBottomPoint, LineType.BLOCKED);
            addLine(topLine);
            addLine(bottomLine);
        }
    }

    private void initTopGoal() {
        int widthCenter = width/2;
        Line line1 = new Line(new Point (widthCenter-1,0),new Point(widthCenter,0),LineType.PLAYER_TOP);
        Line line2 = new Line(new Point (widthCenter,0),new Point(widthCenter+1,0),LineType.PLAYER_TOP);
        Line line3 = new Line(new Point (widthCenter-1,0),new Point(widthCenter-1,1),LineType.PLAYER_TOP);
        Line line4 = new Line(new Point (widthCenter+1,0),new Point(widthCenter+1,1),LineType.PLAYER_TOP);
        addLine(line1);
        addLine(line2);
        addLine(line3);
        addLine(line4);
    }

    private void initBottomGoal() {
        int widthCenter = width/2;
        Line line1 = new Line(new Point (widthCenter-1,height),new Point(widthCenter,height),LineType.PLAYER_BOTTOM);
        Line line2 = new Line(new Point (widthCenter,height),new Point(widthCenter+1,height),LineType.PLAYER_BOTTOM);
        Line line3 = new Line(new Point (widthCenter-1,height),new Point(widthCenter-1,height-1),LineType.PLAYER_BOTTOM);
        Line line4 = new Line(new Point (widthCenter+1,height),new Point(widthCenter+1,height-1),LineType.PLAYER_BOTTOM);
        addLine(line1);
        addLine(line2);
        addLine(line3);
        addLine(line4);
    }
    private void leftSide() {
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

    private void rightSide() {
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

    public boolean hasAnyConnection(Point newPosition) {
        for (Line line: lines) {
            if (line.startPoint.equals(newPosition)||line.endPoint.equals(newPosition)) return true;
        }
        return false;
    }

}
