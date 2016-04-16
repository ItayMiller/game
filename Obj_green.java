

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import java.awt.*;
import java.util.Random;



public class Obj_green extends Obj
{


    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int LEFT = 2;
    public static final int DOWN = 3;
    int mDirection;
    //Constructor - starts when the class begins running.
    Obj_green() {
        size = 40;
        Random rad = new Random();
        x = Animation1.SCREEN_WIDTH / 2;
        y = Animation1.SCREEN_HEIGHT / 2;
        mDirection = rad.nextInt(4);
    }
    void move() {
        Random rand = new Random();

        boolean foundDirection = false;
        while (!foundDirection) {
            int way = rand.nextInt(160); // 98.75 percent continue in current direction , 0.625 percent turn right , 0.625 percent turn left.
            switch (way) {
                case RIGHT:
                    mDirection = (mDirection + 1) % 4;
                    break;
                case LEFT:
                    mDirection = (mDirection - 1);
                    if (mDirection < 0)
                        mDirection = DOWN;
                    break;
                default:
                    break;

            }

            if (mDirection == RIGHT && x + size > Animation1.SCREEN_WIDTH) {
            } else if (mDirection == LEFT && x < RIGHT) {
            } else if (mDirection == DOWN && y + size > Animation1.SCREEN_HEIGHT) {
            } else if (mDirection == UP && y < RIGHT) {
            } else {
                foundDirection = true;

                switch (mDirection) {
                    case UP:
                        y--;
                        break;
                    case RIGHT:
                        x++;
                        break;
                    case LEFT:
                        x--;
                        break;
                    case DOWN:
                        y++;
                        break;
                }

            }
        }
    }
}




