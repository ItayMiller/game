public class level
{
    public  int NUM_OF_OBJ_GREEN = 5;
    public  int NUM_OF_OBJ_RED = 3;
    public  int NUM_OF_OBJ_BLUE = 1;

    Obj_green[] Obj_greens = new Obj_green[NUM_OF_OBJ_GREEN];
    Obj_red[] Obj_reds = new Obj_red[NUM_OF_OBJ_RED];
    Obj_blue[] Obj_blues = new Obj_blue[NUM_OF_OBJ_BLUE];

    public level()
    {
      for (int idx = 0; idx < NUM_OF_OBJ_BLUE; idx++ )
      {
        Obj_blues[idx] = new Obj_blue();
      }

        for (int idx = 0; idx < NUM_OF_OBJ_RED; idx++ )
        {
            Obj_reds[idx] = new Obj_red();
        }

        for (int idx = 0; idx < NUM_OF_OBJ_GREEN; idx++ )
        {
            Obj_greens[idx] = new Obj_green();
        }
    }

}
