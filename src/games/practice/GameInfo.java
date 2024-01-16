package games.practice;

public class GameInfo {
    private int[][] game;
    private int[][] generatedGame;
    private int difficulty;

    public GameInfo(){
        game = new int[10][10];
        generatedGame = new int[][]{
                        {7, 2, 6, 5, 4, 9, 8, 3, 1},
                        {5, 4, 1, 3, 7, 8, 6, 2, 9},
                        {8, 3, 9, 6, 2, 1, 4, 7, 5},
                        {4, 7, 5, 8, 1, 6, 3, 9, 2},
                        {6, 9, 8, 2, 5, 3, 7, 1, 4},
                        {2, 1, 3, 4, 9, 7, 5, 6, 8},
                        {3, 5, 4, 1, 6, 2, 9, 8, 7},
                        {9, 8, 2, 7, 3, 4, 1, 5, 6},
                        {1, 6, 7, 9, 8, 5, 2, 4, 3}
        };
    }

    public int[][] getGame() {
        return game;
    }

    public int[][] getGeneratedGame() {
        return generatedGame;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void createBoard(int selectedDifficulty) {
        difficulty = selectedDifficulty == 0 ? 30 : selectedDifficulty == 1 ? 25 : selectedDifficulty == 2 ? 20 : 15;

        int max = 8;
        int min = 0;
        int range = max - min + 1;

        // Randomly place tiles in the grid
        int i = 0;
        while(i < difficulty){
            int x = (int)(Math.random() * range);
            int y = (int)(Math.random() * range);
            if(game[x][y] == 0){
                game[x][y] = generatedGame[x][y];
                i++;
            }
        }
    }
}
