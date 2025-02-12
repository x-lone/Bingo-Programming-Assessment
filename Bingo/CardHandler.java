package Bingo;

public class CardHandler {
    private String name;
    private int[] values;

    public CardHandler(String name, String values) {
        this.name = name;

        String[] temp = values.split(",");
        this.values = new int[temp.length];

        for (int i = 0; i < temp.length; i++) {
            this.values[i] = Integer.parseInt(temp[i].replace(" ", ""));
        }
    }

    public boolean StampLocation() {

        return false;
    }

    public boolean ValidateCard() {

        return false;
    }

    public int[] GetRow(int row) {
        int[] temp = new int[5];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = values[(row*5)+i];
        }
        return temp;
    }

    public String GetName() {
        return name;
    }
}