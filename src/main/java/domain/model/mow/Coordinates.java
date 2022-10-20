package domain.model.mow;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coordinates {

    int x;
    int y;

    public void up() {
        this.y += 1;
    }

    public void down(){
        this.y -=1 ;
    }

    public void right(){
        this.x +=1;
    }

    public void left(){
        this.x -=1;
    }

}
