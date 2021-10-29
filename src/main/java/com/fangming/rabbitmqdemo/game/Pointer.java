package com.fangming.rabbitmqdemo.game;

/**
 * @author Ming
 * @date 2021/10/15 16:59
 */

import java.awt.Color;
import java.awt.Graphics;

//指示器类
public class Pointer {
    private int i=0;//二维下标i
    private int j=0;//二维下标j
    private int x=0;//坐标X
    private int y=0;//坐标Y
    private main.GamePanel panel=null;
    private Color color=null;
    private int h=40;//指示的大小
    private boolean isShow=false;//是否展示
    private int qizi = 0 ;//棋子类型 0：无  1：白棋  2：黑棋

    public Pointer(int x, int y, int i, int j, Color color, main.GamePanel panel){
        this.x=x;
        this.y=y;
        this.i=i;
        this.j=j;
        this.panel=panel;
        this.color=color;
    }
    //绘制
    void draw(Graphics g){
        Color oColor = g.getColor();
        if(color!=null){
            g.setColor(color);
        }
        if(isShow){
            //绘制指示器
            g.drawRect(x-h/2, y-h/2, h, h);
        }
        if(color!=null){//用完设置回去颜色
            g.setColor(oColor);
        }
    }
    //判断鼠标是否在指针范围内
    boolean isPoint(int x,int y){
        //大于左上角，小于右下角的坐标则肯定在范围内
        if(x>this.x-h/2 && y >this.y-h/2
                && x<this.x+h/2 && y <this.y+h/2){
            return  true;
        }
        return false;
    }
    public boolean isShow() {
        return isShow;
    }
    public void setShow(boolean isShow) {
        this.isShow = isShow;
    }
    public int getQizi() {
        return qizi;
    }
    public void setQizi(int qizi) {
        this.qizi = qizi;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    public int getJ() {
        return j;
    }
    public void setJ(int j) {
        this.j = j;
    }


}

