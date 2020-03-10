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
        Line line1 = new Line(new Point(widthCenter - 1, 0), new Point(widthCenter, 0), LineType.TOPGOAL);
        Line line2 = new Line(new Point(widthCenter, 0), new Point(widthCenter+1, 0), LineType.TOPGOAL);
        Line line3 = new Line(new Point(widthCenter-1, 0), new Point(widthCenter-1, 1), LineType.TOPGOAL);
        Line line4 = new Line(new Point(widthCenter+1, 0), new Point(widthCenter+1, 1), LineType.TOPGOAL);
        addLine(line1);
        addLine(line2);
        addLine(line3);
        addLine(line4);
    }

    private void initBottomGoal() {
        int widthCenter = width/2;
        Line line1 = new Line(new Point(widthCenter - 1, height), new Point(widthCenter, height), LineType.BOTTOMGOAL);
        Line line2 = new Line(new Point(widthCenter, height), new Point(widthCenter+1, height), LineType.BOTTOMGOAL);
        Line line3 = new Line(new Point(widthCenter-1, height), new Point(widthCenter-1, height-1), LineType.BOTTOMGOAL);
        Line line4 = new Line(new Point(widthCenter+1, height), new Point(widthCenter+1, height-1), LineType.BOTTOMGOAL);
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

    public void initBlockedArea(){
        initLeftDownArea();
        initLeftUpArea();
        initLefStraightArea();
        initRightDownArea();
        initRightUpArea();
        initRightStraightArea();
        initTopLeftArea();
        initTopRightArea();
        initTopStraightArea();
        initBottomLeftArea();
        initBottomRightArea();
        initBottomStraightArea();
        initTopGoalLeft();
        initTopGoalRight();
        initTopGoalStraight();
        initBottomGoalLeft();
        initBottomGoalRight();
        initBottomGoalStraight();

    }

    private void initBottomGoalStraight() {
        int widthCenter = width / 2;
        for (int g = 0; g < 3; g++) {
            int x1 = widthCenter + g - 1;
            int y1 = height+1;
            int y2 = height;
            Point start = new Point(x1, y1);
            Point end = new Point(x1, y2);
            Line line = new Line(start, end, LineType.BLOCKED);
            addLine(line);
        }
        Point start = new Point(widthCenter-2, height);
        Point end = new Point(widthCenter-1, height);
        Line line = new Line(start, end, LineType.BLOCKED);
        addLine(line);

        Point start2 = new Point(widthCenter+2, height);
        Point end2 = new Point(widthCenter+1, height);
        Line line2 = new Line(start2, end2, LineType.BLOCKED);
        addLine(line2);
    }

    private void initBottomGoalRight() {
        int widthCenter = width / 2;
        for (int g = 0; g < 3; g++) {
            int x1 = widthCenter + g - 2;
            int x2 = widthCenter + g - 1;
            int y1 = height+1;
            int y2 = height;
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            Line line = new Line(start, end, LineType.BLOCKED);
            addLine(line);

        }
    }

    private void initBottomGoalLeft() {
        int widthCenter = width / 2;
        for (int g = 0; g < 3; g++) {
            int x1 = widthCenter + g - 1;
            int x2 = widthCenter + g;
            int y1 = height;
            int y2 = height + 1;
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            Line line = new Line(start, end, LineType.BLOCKED);
            addLine(line);

        }
    }

    private void initTopGoalStraight() {
        int widthCenter = width / 2;
        for (int g = 0; g < 3; g++) {
            int x1 = widthCenter + g - 1;
            int y1 = -1;
            int y2 = 0;
            Point start = new Point(x1, y1);
            Point end = new Point(x1, y2);
            Line line = new Line(start, end, LineType.BLOCKED);
            addLine(line);
        }
        Point start = new Point(widthCenter-2, 0);
        Point end = new Point(widthCenter-1, 0);
        Line line = new Line(start, end, LineType.BLOCKED);
        addLine(line);

        Point start2 = new Point(widthCenter+2, 0);
        Point end2 = new Point(widthCenter+1, 0);
        Line line2 = new Line(start2, end2, LineType.BLOCKED);
        addLine(line2);

    }

    private void initTopGoalRight() {
        int widthCenter = width / 2;
        for (int g = 0; g < 3; g++) {
            int x1 = widthCenter + g - 2;
            int x2 = widthCenter + g - 1;
            int y1 = -1;
            int y2 = 0;
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            Line line = new Line(start, end, LineType.BLOCKED);
            addLine(line);

        }
    }

    private void initTopGoalLeft() {
        int widthCenter = width / 2;
        for (int g = 0; g < 3; g++) {
                int x1 = widthCenter + g - 1;
                int x2 = widthCenter + g;
                int y1 = 0;
                int y2 = -1;
                Point start = new Point(x1, y1);
                Point end = new Point(x2, y2);
                Line line = new Line(start, end, LineType.BLOCKED);
                addLine(line);

        }
    }

    private void initBottomStraightArea() {
        int widthCenter = width / 2;
        for (int g = 0; g <= width; g++) {
            if (g != widthCenter-1 && g != widthCenter && g != widthCenter+1) {
                int x = g;
                int y1 = height-1;
                int y2 = height;
                Point start = new Point(x, y1);
                Point end = new Point(x, y2);
                Line line = new Line(start, end, LineType.BLOCKED);
                addLine(line);
            }
        }
    }

    private void initBottomRightArea() {
        int widthCenter = width / 2;
        for (int g = 0; g < width; g++) {
            if (g != widthCenter-1 && g != widthCenter) {
                int x1 = g;
                int x2 = g + 1;
                int y1 = height-1;
                int y2 = height;
                Point start = new Point(x1, y1);
                Point end = new Point(x2, y2);
                Line line = new Line(start, end, LineType.BLOCKED);
                addLine(line);
            }
        }
    }

    private void initBottomLeftArea() {
        int widthCenter = width / 2;
        for (int g = 0; g < width; g++) {
            if (g != widthCenter - 1 && g != widthCenter) {
                int x1 = g;
                int x2 = g+1;
                int y1 = height;
                int y2 = height - 1;
                Point start = new Point(x1, y1);
                Point end = new Point(x2, y2);
                Line line = new Line(start, end, LineType.BLOCKED);
                addLine(line);
            }
        }
    }

    private void initTopStraightArea() {
        int widthCenter = width / 2;
        for (int g = 0; g <= width; g++) {
            if (g != widthCenter-1 && g != widthCenter && g != widthCenter+1) {
                int x = g;
                int y1 = 1;
                int y2 = 0;
                Point start = new Point(x, y1);
                Point end = new Point(x, y2);
                Line line = new Line(start, end, LineType.BLOCKED);
                addLine(line);
            }
        }
    }

    private void initTopRightArea() {
        int widthCenter = width / 2;
        for (int g = 0; g < width; g++) {
            if (g != widthCenter-1 && g != widthCenter) {
                int x1 = g;
                int x2 = g + 1;
                int y1 = 1;
                int y2 = 0;
                Point start = new Point(x1, y1);
                Point end = new Point(x2, y2);
                Line line = new Line(start, end, LineType.BLOCKED);
                addLine(line);
            }
        }
    }

    private void initTopLeftArea() {
        int widthCenter = width / 2;
        for (int g = 1; g <= width; g++) {
            if (g != widthCenter && g != widthCenter + 1) {
                int x1 = g - 1;
                int x2 = g;
                int y1 = 0;
                int y2 = 1;
                Point start = new Point(x1, y1);
                Point end = new Point(x2, y2);
                Line line = new Line(start, end, LineType.BLOCKED);
                addLine(line);
            }
        }
    }

    private void initRightStraightArea() {
        for (int g = 1; g < height; g++) {
            int x1 = width+1;
            int x2 = width;
            int y = g;
            Point start = new Point(x1, y);
            Point end = new Point(x2, y);
            Line line = new Line(start, end, LineType.BLOCKED);
            addLine(line);
        }
    }

    private void initRightUpArea() {
        for (int g = 1; g < height; g++) {
            int x1 = width+1;
            int x2 = width;
            int y1 = g - 1;
            int y2 = g;
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            Line line = new Line(start, end, LineType.BLOCKED);
            addLine(line);
        }
    }

    private void initRightDownArea() {
        for (int g = 1; g < height; g++) {
            int x1 = width;
            int x2 = width+1;
            int y1 = g;
            int y2 = g+1;
            Point start = new Point(x1,y1);
            Point end = new Point(x2,y2);
            Line line = new Line(start,end,LineType.BLOCKED);
            addLine(line);
        }
    }

    private void initLefStraightArea() {
        for (int g = 1; g < height; g++) {
            int x1 = -1;
            int x2 = 0;
            int y = g;
            Point start = new Point(x1, y);
            Point end = new Point(x2, y);
            Line line = new Line(start, end, LineType.BLOCKED);
            addLine(line);
        }
    }

    private void initLeftUpArea() {
        for (int g = 1; g < height; g++) {
            int x1 = -1;
            int x2 = 0;
            int y1 = g - 1;
            int y2 = g;
            Point start = new Point(x1, y1);
            Point end = new Point(x2, y2);
            Line line = new Line(start, end, LineType.BLOCKED);
            addLine(line);
        }
    }

    private void initLeftDownArea() {
        for (int g = 1; g < height; g++) {
            int x1 = 0;
            int x2 = -1;
            int y1 = g;
            int y2 = g+1;
            Point start = new Point(x1,y1);
            Point end = new Point(x2,y2);
            Line line = new Line(start,end,LineType.BLOCKED);
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

    public boolean lineBlocked(Line line) {
        return false;
    }
}
