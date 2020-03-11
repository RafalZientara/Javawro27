package pl.sda.rafal.zientara.tdd.PaperFootball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class FootballView extends JTextField
        implements FootballContract.View {
    private static final int CELL_PADDING = 1;
    private FootballBoard board;
    private Point currentPosition;
    private LineType player = LineType.PLAYER_TOP;
    private FootballContract.Presenter presenter;

    public FootballView() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_NUMPAD8:
                        presenter.moveTop();
                        break;
                    case KeyEvent.VK_NUMPAD9:
                        presenter.moveTopRight();
                        break;
                    case KeyEvent.VK_NUMPAD6:
                        presenter.moveRight();
                        break;
                    case KeyEvent.VK_NUMPAD3:
                        presenter.moveBottomRight();
                        break;
                    case KeyEvent.VK_NUMPAD2:
                        presenter.moveBottom();
                        break;
                    case KeyEvent.VK_NUMPAD1:
                        presenter.moveBottomLeft();
                        break;
                    case KeyEvent.VK_NUMPAD4:
                        presenter.moveLeft();
                        break;
                    case KeyEvent.VK_NUMPAD7:
                        presenter.moveTopLeft();
                        break;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(Color.lightGray);
        int cellW = getWidth() / (board.width+2*CELL_PADDING);
        int cellH = getHeight() / (board.height+2*CELL_PADDING);
        drawNet(g, cellW, cellH);
        List<Line> lines = board.getLines();
        g.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5));
        for (Line line : lines) {
            g.setColor(getLineColor(line.type));
            g.drawLine(
                    (line.startPoint.x + CELL_PADDING)* cellW,
                    (line.startPoint.y +CELL_PADDING) * cellH,
                    (line.endPoint.x + CELL_PADDING) * cellW,
                    (line.endPoint.y + CELL_PADDING) * cellH);
        }
        if (currentPosition != null) {

            int r = 5;
            int x = (currentPosition.x + CELL_PADDING)* cellW - r;
            int y = (currentPosition.y + CELL_PADDING)* cellH - r;
            g.setColor(getLineColor(player));
            g.drawOval(x, y, r * 2, r * 2);
        }

    }

    private void drawNet(Graphics g, int cellW, int cellH) {
        for (int i = 0; i <= board.height; i++) {
            int y = (i +CELL_PADDING) * cellH;
            int x1 = CELL_PADDING;
            int x2 = getWidth() - +CELL_PADDING;
            g.drawLine(x1, y, x2, y);
        }
        for (int i = 0; i <= board.width; i++) {
            int x = (i+CELL_PADDING) * cellW;
            int y1 = CELL_PADDING;
            int y2 = getHeight()-CELL_PADDING;
            g.drawLine(x, y1, x, y2);
        }
    }

    public Color getLineColor(LineType type) {
        switch (type) {
            case SIDE:
                return Color.BLACK;
            case PLAYER_TOP:
                return Color.GREEN;
            case PLAYER_BOTTOM:
                return Color.BLUE;
        }
        return Color.WHITE;
    }


    public void setBoard(FootballBoard board) {
        this.board = board;

    }


    @Override
    public void updatePosition(Point currentPosition) {
        this.currentPosition = currentPosition;
        repaint();
    }

    @Override
    public void updateCurrentPlayer(LineType player) {
        this.player = player;
    }

    public void setPresenter(FootballContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
