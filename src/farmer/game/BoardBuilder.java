package farmer.game;

import farmer.animals.AnimalFactory;

public class BoardBuilder {

    public final int INITIAL_HERD_OF_RABBITS_SIZE = 1; //for herd of rabbits
    public final int INITIAL_HERD_SIZE = 0; //for other herds
    public final int INDEX_OF_PEN_FOR_RABBITS = 0;
    public final int INDEX_OF_PEN_FOR_SHEEP = 1;
    public final int INDEX_OF_PEN_FOR_PIGS = 2;
    public final int INDEX_OF_PEN_FOR_COWS = 3;
    public final int INDEX_OF_PEN_FOR_HORSES = 4;


    private Board board;

    public BoardBuilder() {
        this.board = new Board();
    }

    public Board build(){
        return board;
    }

    public void placePen(Pen pen, int index){}



    public void placePenOnBoard(Pen pen, int index){
    board.setPen(pen, index);
    }

    public BoardBuilder rabbitsPen(){
        Pen pen = new Pen(AnimalFactory.rabbit(), INITIAL_HERD_OF_RABBITS_SIZE);
        placePenOnBoard(pen, INDEX_OF_PEN_FOR_RABBITS);
        return this; //self reference

    }
    public BoardBuilder sheepsPen(){
        Pen pen = new Pen(AnimalFactory.sheep(), INITIAL_HERD_SIZE);
        placePenOnBoard(pen, INDEX_OF_PEN_FOR_SHEEP);
        return this; //self reference
    }
    public BoardBuilder pigsPen(){
        Pen pen = new Pen(AnimalFactory.pig(), INITIAL_HERD_SIZE);
        placePenOnBoard(pen, INDEX_OF_PEN_FOR_PIGS);
        return this; //self reference
    }
    public BoardBuilder cowsPen(){
        Pen pen = new Pen(AnimalFactory.cow(), INITIAL_HERD_SIZE);
        placePenOnBoard(pen, INDEX_OF_PEN_FOR_COWS);
        return this; //self reference
    }
    public BoardBuilder horsesPen(){
        Pen pen = new Pen(AnimalFactory.horse(), INITIAL_HERD_SIZE);
        placePenOnBoard(pen, INDEX_OF_PEN_FOR_HORSES);
        return this; //self reference
    }



}
