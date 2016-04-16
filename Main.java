
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import  java.lang.*;
import java.util.*;

 class Animation1

{
    static JFrame frame;

    static private int oneX = 7;
    static private int oneY = 7;

    static int Vx;   // X speed
    static int Vy;   // Y speed

    static boolean keyWasPressed = false;
    static boolean NewGame = false;
    static int replay = 0;
    public static int lastKeyCode = 0;

    static final int project_speed = 97;


    static final int w = 119;
    static final int a = 97;
    static final int s = 115;
    static final int d = 100;

    public static int WALL_X;
    public static int WALL_Y;

    static final int LEFT_ARROW = 37;
    static final int UP_ARROW = 38;
    static final int RIGHT_ARROW = 39;
    static final int DOWN_ARROW = 40;

    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;

    static final int MAX_LIFE = 3;
    static int lifeLeft = MAX_LIFE;
    static boolean gameOver = false;
    static boolean TIMER = false;

    static final int BALL_SIZE = 60;
    static final int BALL_SPEED = 2;

    static final int GAME_SPEED = 5;


    public int getBALL_SIZE() {
        return BALL_SIZE;
    }
    static final int BALL_TRAIL = 2;
    public static final Color OBJ_COLOR = Color.GREEN;

    public static final Color OBJ_RED = Color.red;
    public static final Color TEXT = Color.RED;
    public static final Color LIFE_COLOR = Color.blue;
    public static final Color TIMER_COLOR = Color.ORANGE;
    public static final Color SCORE = Color.green;
    public static final Color R_COLOR = Color.RED;
    public static final Color BLUE_COLOR = Color.blue;

    static int curFrame = 0;
    static public int size;
    public static int sec = 0;
    public static int min = 0;

    static public int CURRENT_LEVEL = 1;
    public static final int NUMBER_OF_LEVELS = 2;
    static level[] levels = new level[NUMBER_OF_LEVELS];
    static level curLevel = levels[CURRENT_LEVEL];


    static Obj[] Objs = new Obj[6];




    public static void main(String[] args)
    {


        frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DrawPanel drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.setResizable(false);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.addKeyListener(drawPanel);

        for (int idx = 0; idx < NUMBER_OF_LEVELS; idx++)
        {
            levels[idx] = new level();
        }

        moveIt();

    }

    //------------------------------------------------------------
    static boolean check_if_touch(Obj Obj) {
        boolean up, down, right, left;
        up = oneY + BALL_SIZE < Obj.y;
        down = oneY > Obj.y + Obj.size;
        right = oneX + BALL_SIZE < Obj.x;
        left = oneX > Obj.x + Obj.size;

        if (!up && !down && !left && !right)
            return true;
        else
            return false;

    }
    //------------------------------------------------------------


    static class DrawPanel extends JPanel implements KeyListener {
        private static final long serialVersionUID = 1L;

        public void paintComponent(Graphics g) {
            //timer here.

            Font TimerFont = new Font("Serif", Font.BOLD, 30);
            g.setFont(TimerFont);
            g.setColor(TIMER_COLOR);


            if (!gameOver) {
                if (sec < 10) {
                    g.drawString(min + ":" + "0" + sec, 993, 70);

                } else {
                    g.drawString(min + ":" + sec, 993, 70);
                }

            }

            if (gameOver) {
                Font myFont = new Font("Serif", Font.BOLD, 80);
                g.setFont(myFont);
                g.setColor(TEXT);
                g.drawString("GAME OVER", 300, 300);
                Font ScoreFront = new Font("Serif", Font.BOLD, 50);
                g.setColor(SCORE);
                g.setFont(ScoreFront);
                g.drawString("Your time is: " + min + ":" + sec, 355, 390);

                // Font R_FONT = new Font("ITALIC", Font.BOLD, 20);
                //  g.setFont(R_FONT);
                //  g.setColor(R_COLOR);
                //  g.drawString("R - replay" , 67 , 40);
                if (lastKeyCode == 82) {
                    NewGame = true;

                }
                if (NewGame) {
                    reInitialize();
                    lifeLeft = MAX_LIFE;

                }

                return;
            }

            // static Objects here
            g.setColor(OBJ_COLOR);

            level curLevel = levels[CURRENT_LEVEL];

            for (int idx = 0; idx < curLevel.NUM_OF_OBJ_GREEN; idx++)
                g.fillRect(curLevel.Obj_greens[idx].x, curLevel.Obj_greens[idx].y,
                        curLevel.Obj_greens[idx].size,
                        curLevel.Obj_greens[idx].size);

            g.setColor(BLUE_COLOR);
            for (int jdx = 0; jdx < curLevel.NUM_OF_OBJ_BLUE; jdx++)
            {
                g.fillRect(curLevel.Obj_blues[jdx].x,
                        curLevel.Obj_blues[jdx].y,
                        curLevel.Obj_blues[jdx].size,
                        curLevel.Obj_blues[jdx].size);
            }
            for (int idx = 0; idx < curLevel.NUM_OF_OBJ_RED; idx++)
            {
                g.fillRect(curLevel.Obj_reds[idx].x, curLevel.Obj_reds[idx].y, curLevel.Obj_reds[idx].size, curLevel.Obj_reds[idx].size);
            }
            Color color = Color.BLACK;

            if (keyWasPressed) {
                Vy = 0;
                Vx = 0;

                switch (lastKeyCode) {
                    case RIGHT_ARROW:
                        Vx = BALL_SPEED;
                        break;
                    case LEFT_ARROW:
                        Vx = -BALL_SPEED;
                        break;
                    case UP_ARROW:
                        Vy = -BALL_SPEED;
                        break;
                    case DOWN_ARROW:
                        Vy = BALL_SPEED;
                        break;
                    case 82:
                        reInitialize();
                        lifeLeft = MAX_LIFE;
                        min = 0;
                        sec = 0;
                        break;


                }
            }

            g.setColor(color);
            g.fillRect(oneX, oneY, BALL_SIZE, BALL_SIZE);
            g.fillRect(oneX - BALL_TRAIL * Vx, oneY - BALL_TRAIL * Vy, BALL_SIZE, BALL_SIZE);

            Font LifeFont = new Font("ITALIC", Font.BOLD, 20);
            g.setFont(LifeFont);
            g.setColor(LIFE_COLOR);
            g.drawString("Life: " + lifeLeft, 990, 30);
            Font R_FONT = new Font("ITALIC", Font.BOLD, 20);
            g.setFont(R_FONT);
            g.setColor(R_COLOR);
            g.drawString("R - replay", 67, 40);

        }

        @Override
        public void keyTyped(KeyEvent e) {
            replay = e.getKeyCode();
            NewGame = true;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            lastKeyCode = e.getKeyCode();
            keyWasPressed = true;
        }


        @Override
        public void keyReleased(KeyEvent e) {
            keyWasPressed = false;
        }
    }

    private static void moveIt() {
        int timer = 0;
        while (true) {

            curFrame++;

            if (oneX >= SCREEN_WIDTH - 4 - BALL_SIZE)
                Vx = -BALL_SPEED;
            if (oneX <= 0)
                Vx = BALL_SPEED;
            if (oneY >= SCREEN_HEIGHT - 28 - BALL_SIZE)
                Vy = -BALL_SPEED;
            if (oneY <= 0)
                Vy = BALL_SPEED;

            oneX += Vx;
            oneY += Vy;

            try {

                Thread.sleep(GAME_SPEED);
                timer++;
                if (!gameOver) {
                    if (timer == 1000 / GAME_SPEED) {
                        sec++;
                        if (sec == 60) {
                            sec = 0;
                            min++;
                        }
                        timer = 0;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }

            level curLevel = levels[CURRENT_LEVEL];

            for (int idx = 0; idx < curLevel.NUM_OF_OBJ_GREEN; idx++)
            {
                if (check_if_touch(curLevel.Obj_greens[idx])) {
                    lifeLeft--;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reInitialize();
                }
                curLevel.Obj_greens[idx].move();


            }
            for (int jdx = 0; jdx < curLevel.NUM_OF_OBJ_BLUE; jdx++) {
                if (check_if_touch(curLevel.Obj_blues[jdx])) {
                    lifeLeft--;
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reInitialize();
                }
                curLevel.Obj_blues[jdx].move();

            }


            if (lifeLeft < 1) {
                gameOver = true;


            }


            frame.repaint();


        }
    }

    static void reInitialize()
    {
     int kdx = 0 , jdx = 0;
        for (int idx = 0; idx < Objs.length; idx++)
        {


            Objs[idx] = null;
            Objs[idx] = new Obj_green();
            Objs[idx] = new Obj_blue();
            oneX = 1;
            oneY = 1;
        }


    }
}
