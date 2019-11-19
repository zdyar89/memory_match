package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Observable;


public class GameBoard extends Observable
{
    //will need to update with more pieces later

    private int cols;
    private int rows;
    private String color;
    private Jewel[][] grid;


    public GameBoard(int rows, int cols, String color)
    {
        //May need to implement a 2-D array, would also think about a DFS or BFS solution
        //The logic needs to see if a chain of three or more shapes lineup vertically or horizontally
        this.rows = rows;
        this.cols = cols;
        this.color = color;
        this.grid = new Jewel[rows][cols];

    }

    public Jewel[][] makeGrid()
    {
        Jewel[][] grid = new Jewel[rows][cols];
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < cols; c++){
                grid[r][c] = getRandomJewel();
            }
        }
        return grid;
    }

    public Jewel getRandomJewel()
    {
        Random randNum = new Random();
        //update total gems when all pieces are added to final design
        int totalGems = 2;
        int indexSlot = randNum.nextInt(totalGems) + 1;

        return Jewel.values()[indexSlot];
    }

    public Jewel getJewel(int r, int c)
    {
        return grid[r][c];
    }

    public void setJewel(int r, int c, Jewel gem)
    {
        grid[r][c] = gem;
        updatePos(EventType.SWITCH, r, c);
    }

    public void switchPos(int originR, int originC, int swapR, int swapC)
    {
        Jewel gemOne = getJewel(originR, originC);
        Jewel gemTwo = getJewel(swapR, swapC);

        setJewel(originR, originC, gemTwo);
        setJewel(swapR, swapC, gemOne);

    }

    public List<Jewel> getRow(int r)
    {
        ArrayList<Jewel> gems = new ArrayList<>();
        for(int c = 0; c < cols; c++)
        {
            gems.add(grid[r][c]);
        }

        return gems;
    }

    public List<Jewel> getCol(int c)
    {
        ArrayList<Jewel> gems = new ArrayList<>();
        for(int r = 0; r < rows; r++)
        {
            gems.add(grid[r][c]);
        }

        return gems;
    }

    public void placeEmpty(int r, int c)
    {
        if(r == 0){
            setJewel(r, c, getRandomJewel());
        }
        else
        {
            Jewel gem1 = getJewel(r - 1, c);
            setJewel(r - 1, c, Jewel.Empty);
            setJewel(r, c, gem1);
            removeOneGem(r - 1, c);
        }
    }

    public void removeJewels(List<GemPos> combo)
    {
        for(GemPos p : combo)
        {
            setJewel(p.row, p.col, Jewel.Empty);
            updatePos(EventType.REMOVAL, p.row, p.col);
        }

        for(GemPos p: combo)
        {
            placeEmpty(p.row, p.col);
        }
    }

    public void removeOneGem(int r, int c)
    {
        setJewel(r, c, Jewel.Empty);
        placeEmpty(r, c);
    }



    public void updatePos(EventType type, int r, int c)
    {
        GemPos pos = new GemPos(r, c);
        //fix GameEvent class and the errors should clear out
        //GameEvent instance = new GameEvent(type, pos);

        setChanged();
        //notifyObservers(instance);
    }

}

//since I'm using actually classes for the jewels, this enum might not be needed

enum Jewel
{
    Purple, Orange, Blue, Empty
}

enum EventType
{
    SWITCH, REMOVAL
}

class GemPos
{
    public int row;
    public int col;

    public GemPos(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
}
class BoardEvent
{
    public EventType event;
    public GemPos pos;

    public BoardEvent(EventType event, GemPos pos)
    {
        this.event = event;
        this.pos = pos;
    }
}
