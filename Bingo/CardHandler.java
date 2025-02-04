package Bingo;

public class CardHandler {
    private String name;
    private int[][] values;

    public CardHandler(String name, int[][] values) {
        this.name = name;
        this.values = values;
    }

    public boolean StampLocation() {

        return false;
    }

    public boolean ValidateCard() {

        return false;
    }

    public void DrawCard() {
        System.out.println("------" + name + "------");
        System.out.println("    B I N G O    ");
        System.out.println("-+--+--+--+--+--+");
        for (int y = 0; y < 5; y++) {
            System.out.print("BINGO".charAt(y) + "|");
            for (int x = 0; x < 5; x++) {
                System.out.printf("%2s|", values[y][x]);
            }
            System.out.println("\n-+--+--+--+--+--+");
        }
    }

    public void DrawLine() {

    }
}
