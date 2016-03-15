import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        ChessBoard.runGui(game);

        //game loop
        while (!game.isOver) {
            game.turnIsOver = false;
            while (!game.turnIsOver) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    System.out.println("Exception caught");
                }
            }
        }
    }
}

