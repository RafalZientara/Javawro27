package pl.sda.rafal.zientara.programowanie2.lesson6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class FootballView extends JTextField implements FootballContract.View {
    private FootballBoard board = new FootballBoard(10, 12);
    private Point pointPosition;
    private LineType player;
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
                    case KeyEvent
                            .VK_NUMPAD8:
                        presenter.moveTop();
                        break;
                    case KeyEvent
                            .VK_NUMPAD9:
                        presenter.moveTopRight();
                        break;
                    case KeyEvent
                            .VK_NUMPAD6:
                        presenter.moveRight();
                        break;
                    case KeyEvent
                            .VK_NUMPAD3:
                        presenter.moveBottomRight();
                        break;
                    case KeyEvent
                            .VK_NUMPAD2:
                        presenter.moveBottom();
                        break;
                    case KeyEvent
                            .VK_NUMPAD1:
                        presenter.moveBottomLeft();
                        break;
                    case KeyEvent
                            .VK_NUMPAD4:
                        presenter.moveLeft();
                        break;
                    case KeyEvent
                            .VK_NUMPAD7:
                        presenter.moveTopLeft();
                        break;
                    default:
                        System.out.println("olewam" + e);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g.setColor(Color.MAGENTA);
        g.setColor(Color.LIGHT_GRAY);
        int cellW = getWidth() / board.width;
        int cellH = getHeight() / board.height;
        for (int i = 0; i <= board.height; i++) {
            int y = i * cellH;
            int x1 = 0;
            int x2 = getWidth();
            g.drawLine(x1, y, x2, y);
        }
        for (int i = 0; i <= board.width; i++) {
            int x = i * cellW;
            int y1 = 0;
            int y2 = getHeight();
            g.drawLine(x, y1, x, y2);
        }
        g.drawLine(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);
        List<Line> lines = board.getLines();
        for (Line line : lines) {
            g.setColor(getLineColor(line.type));
            g.drawLine(line.start.x * cellW,
                    line.start.y * cellH,
                    line.end.x * cellW,
                    line.end.y * cellH);
        }
        if (pointPosition != null) {
            int r = 5;
            int x = pointPosition.x * cellW - r;
            int y = pointPosition.y * cellH - r;
            g.setColor(getLineColor(player));
            g.drawOval(x, y, r * 2, r * 2);
        }

    }

    private Color getLineColor(LineType type) {
        switch (type) {
            case SIDE:
                return Color.BLACK;
            case PLAYER_TOP:
                return Color.YELLOW;
            case PLAYER_BOTTOM:
                return Color.BLUE;
        }
        return Color.RED;
    }

    public void setBoard(FootballBoard board) {
        this.board = board;
    }

    @Override
    public void updatePosition(Point pointPosition) {
        this.pointPosition = pointPosition;
//        invalidate();
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
