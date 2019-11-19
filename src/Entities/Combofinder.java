package Entities;

import java.util.List;

import edu.utc.game.GameObject;

public class Combofinder extends GameObject
{
    private static final int MIN_LINK_LENGTH = 3;
    private GameBoard grid;

    public Combofinder(GameBoard grid)
    {
        this.grid = grid;
    }

    public boolean swapCheck(int originRow, int originCol, int swapRow, int swapCol)
    {
        int delX = Math.abs(originRow - swapRow);
        int delY = Math.abs(originCol - swapCol);

        if(delX == 0 && delY == 1) return true;
        else if(delX == 1 && delY == 0) return true;
        else
        {
            return false;
        }

    }

   /* public List<GemPos> rowCombo(int r)
    {
        List<Jewel> gems = grid.g
    }

    public boolean swapRow(int r)
    {
        List<> combo =
    }*/

}

class Combo
{
    public int start;
    public int end;
    public int length;
}
