package games.practice;

public class GameInfo {
    private int[][] game;
    private int[][] generatedGame;

    public GameInfo(int difficulty){
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

        int revealedTiles = difficulty == 0 ? 20 : difficulty == 1 ? 15 : difficulty == 2 ? 10 : 5;

        int max = 8;
        int min = 0;
        int range = max - min + 1;

        // Randomly place tiles in the grid
        for(int i = 0; i < revealedTiles; i++){
            int x = (int)(Math.random() * range);
            int y = (int)(Math.random() * range);
            if(game[x][y] == 0){
                game[x][y] = generatedGame[x][y];
            }
        }
    }

    public int[][] getGame() {
        return game;
    }

    public void setGame(int[][] game) {
        this.game = game;
    }

    public int[][] getGeneratedGame() {
        return generatedGame;
    }
}
