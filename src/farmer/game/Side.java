package farmer.game;

import farmer.animals.Animal;

public class Side {

    private Animal resultOfFirtRolling;
    private Animal resultOfSecondRolling;

    public Side(Animal resultOfFirtRolling, Animal resultOfSecondRolling) {
        this.resultOfFirtRolling = resultOfFirtRolling;
        this.resultOfSecondRolling = resultOfSecondRolling;
    }

    public Animal getResultOfFirtRolling() {
        return resultOfFirtRolling;
    }

    public void setResultOfFirtRolling(Animal resultOfFirtRolling) {
        this.resultOfFirtRolling = resultOfFirtRolling;
    }

    public Animal getResultOfSecondRolling() {
        return resultOfSecondRolling;
    }

    public void setResultOfSecondRolling(Animal resultOfSecondRolling) {
        this.resultOfSecondRolling = resultOfSecondRolling;
    }
}
