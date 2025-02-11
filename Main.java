import Bingo.GameHander;

public class Main {
    public static void main(String[] args) {
        GameHander game = new GameHander("BingoCards.txt");
        game.run();
    }
}