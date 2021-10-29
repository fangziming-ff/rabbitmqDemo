package com.fangming.rabbitmqdemo.game;

/**
 * @author Ming
 * @date 2021/10/15 16:50
 */
public class GameRun {

    //主类
    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        main.GamePanel gamePanel = new main.GamePanel(frame);
        frame.add(gamePanel);
        frame.setVisible(true);//设定显示
    }


}
