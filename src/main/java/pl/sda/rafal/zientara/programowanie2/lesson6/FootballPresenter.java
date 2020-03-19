package pl.sda.rafal.zientara.programowanie2.lesson6;

public class FootballPresenter implements FootballContract.Presenter {

    private final FootballContract.View view;
    private final FootballBoard board;

    private Point currentPosition;
    private boolean playerTopTurn = true;

    public FootballPresenter(FootballContract.View view, FootballBoard board) {
        this.view = view;
        this.board = board;
    }

    @Override
    public void init() {
        currentPosition = new Point(board.width/2,board.height/2);
        view.updatePosition(currentPosition);
    }

    @Override
    public void moveTop() {
        move(0,-1);
    }

    @Override
    public void moveTopRight() {
        move(1,-1);
    }

    @Override
    public void moveRight() {
        move(1,0);
    }

    @Override
    public void moveBottomRight() {
        move(1,1);
    }

    @Override
    public void moveBottom() {
        move(0,1);
    }

    @Override
    public void moveBottomLeft() {
        move(-1,1);
    }

    @Override
    public void moveLeft() {
        move(-1,0);
    }

    @Override
    public void moveTopLeft() {
        move(-1,-1);
    }

    private void move(int x, int y){
        //x E <-1,1>
        //y E <-1,1>
        Point newPosition = new Point(currentPosition.x+x,currentPosition.y+y);
        System.out.println(getTypeByTurn());
        if(!board.lineExists(currentPosition,newPosition) && !playerTopWin() && !playerBottomWin()){
            LineType type = getTypeByTurn();
            if(!board.hasAnyConnection(newPosition)){
                playerTopTurn = !playerTopTurn;
            }
            board.addLine(new Line(currentPosition,newPosition,type));
            currentPosition = newPosition;
            view.updatePosition(currentPosition);
            view.updateCurrentPlayer(getTypeByTurn());
        }

    }

    private LineType getTypeByTurn() {
        return playerTopTurn ? LineType.PLAYER_TOP : LineType.PLAYER_BOTTOM;
    }

    private boolean playerTopWin() {
        int widthCenter = board.width / 2;
        if (getTypeByTurn()==LineType.PLAYER_TOP && (currentPosition.equals(new Point(widthCenter - 1, 0)) || currentPosition.equals(new Point(widthCenter, 0))
                || currentPosition.equals(new Point(widthCenter + 1, 0)))) {
            //System.out.println("Player Top win!");
            return true;
        }
        return false;
    }

    private boolean playerBottomWin(){
        int widthCenter = board.width / 2;
        if(getTypeByTurn()==LineType.PLAYER_BOTTOM && (currentPosition.equals(new Point(widthCenter - 1, board.height)) || currentPosition.equals(new Point(widthCenter,board.height))
                || currentPosition.equals(new Point(widthCenter + 1,board.height)))){
            //System.out.println("Player Bottom win!");
            return true;
        }
        return false;
    }

    @Override
    public MatchResult checkResult(){
        if (playerTopWin()) return MatchResult.PLAYER_TOP_WIN;
        if (playerBottomWin()) return  MatchResult.PLAYER_BOTTOM_WIN;
        return MatchResult.PLAY;
    }

    public boolean ifGameIsWin(){
        if (checkResult()==MatchResult.PLAYER_TOP_WIN || checkResult()==MatchResult.PLAYER_BOTTOM_WIN){
            return true;
        }
        return false;
    }

    public Point getCurrentPosition(){
        return currentPosition;
    }
}
