

import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.text.StyledEditorKit;
import  java.lang.*;


public class Obj_red extends Obj
{
    public int Red_Direction;  //0-UP  1- RIGHT  2-LEFT  3-DOWN.
    int size = 50;

    Obj_red()
    {
        Random rad = new Random();
        x = Animation1.SCREEN_WIDTH / 2;
        y = Animation1.SCREEN_HEIGHT / 2;

    }

    public void move()
    {

        switch (Red_Direction) {
            case 0:
                y--;
                break;
            case 1:
                x++;
                break;
            case 2:
                x--;
                break;
            case 3:
                y++;
                break;
        }



    }

}


