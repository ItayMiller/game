

public class Obj_blue extends Obj {
    int speed;
    int first_x;
    int first_y;
    int length;
    int dir;

    public Obj_blue()
    {
        if( dir == LEFT || dir == UP)  speed *= -1;

    }


    void move()
    {
        if (dir == LEFT || dir  == RIGHT)
        { // if it's an X axis movement
            x += speed; // advance
            if ((x - first_x )*(x - first_x) >= length*length )
            { // absolute value
                speed *= -1; // change direction
            }
        }
        else
        {
            y += speed; // advance
            if ((y - first_y )*(y - first_y) >= length*length )
            { // absolute value
                speed *= -1; // change direction
            }
        }
    }
}
