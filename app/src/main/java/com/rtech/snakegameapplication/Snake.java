package com.rtech.snakegameapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Snake {
    private boolean move_left, move_right, move_top, move_Bottom;
    private Bitmap bm, bm_head_up, bm_head_down, bm_head_left, bm_head_right, bm_body_vertical, bm_body_horizental,
            bm_body_top_right, bm_body_top_left, bm_body_bottom_right, bm_body_bottom_left, bm_tail_right, bm_tail_left, bm_tail_up,
            bm_tail_down;
    private int x, y, lenght;
    private ArrayList<PartSnake> arrPartSnake = new ArrayList<>();

    public Snake(Bitmap bm, int x, int y, int length) {
        this.bm = bm;
        this.lenght = length;
        bm_body_bottom_left = Bitmap.createBitmap(bm, 0, 0, bm.getWidth()/14, bm.getHeight());
        bm_body_bottom_right = Bitmap.createBitmap(bm, bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_body_horizental = Bitmap.createBitmap(bm, 2*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_body_top_left = Bitmap.createBitmap(bm, 3*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_body_top_right = Bitmap.createBitmap(bm, 4*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_body_vertical = Bitmap.createBitmap(bm, 5*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_head_down = Bitmap.createBitmap(bm, 6*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_head_left = Bitmap.createBitmap(bm, 7*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_head_right = Bitmap.createBitmap(bm, 8*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_head_up = Bitmap.createBitmap(bm, 9*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_tail_up = Bitmap.createBitmap(bm, 10*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_tail_right = Bitmap.createBitmap(bm, 11*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_tail_left = Bitmap.createBitmap(bm, 12*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        bm_tail_down = Bitmap.createBitmap(bm, 13*bm.getWidth()/14, 0, bm.getWidth()/14, bm.getHeight());
        setMove_right(true);
        arrPartSnake.add(new PartSnake(bm_head_right, x, y));
        for (int i = 1; i < length-1; i++){
            this.arrPartSnake.add(new PartSnake(bm_body_horizental, this.arrPartSnake.get(i-1).getX()-GameView.sizeofMap, y));
        }
        arrPartSnake.add(new PartSnake(bm_tail_right, arrPartSnake.get(length-2).getX()-GameView.sizeofMap, arrPartSnake.get(length-2).getY()));
    }

    public void update(){
        for(int i = lenght-1; i > 0; i--){
            arrPartSnake.get(i).setX(arrPartSnake.get(i-1).getX());
            arrPartSnake.get(i).setY(arrPartSnake.get(i-1).getY());
        }
        if(move_right){
            arrPartSnake.get(0).setX(arrPartSnake.get(0).getX()+GameView.sizeofMap);
            arrPartSnake.get(0).setBm(bm_head_right);
        }else if(move_Bottom){
            arrPartSnake.get(0).setY(arrPartSnake.get(0).getY()+GameView.sizeofMap);
            arrPartSnake.get(0).setBm(bm_head_down);
        }else if(move_top){
            arrPartSnake.get(0).setY(arrPartSnake.get(0).getY()-GameView.sizeofMap);
            arrPartSnake.get(0).setBm(bm_head_up);
        }else{
            arrPartSnake.get(0).setX(arrPartSnake.get(0).getX()-GameView.sizeofMap);
            arrPartSnake.get(0).setBm(bm_head_left);
        }
        for (int i = 1; i < lenght - 1; i++){
            if(arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrBottom().intersect(arrPartSnake.get(i-1).getrBody())
                    ||arrPartSnake.get(i).getrBottom().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i-1).getrBody())){
                arrPartSnake.get(i).setBm(bm_body_bottom_left);
            }else if (arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i-1).getrBody())
                    ||arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i+1).getrBody())){
                arrPartSnake.get(i).setBm(bm_body_top_left);
            }else if (arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i-1).getrBody())
                    ||arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i+1).getrBody())) {
                arrPartSnake.get(i).setBm(bm_body_top_right);
            }else if(arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrBottom().intersect(arrPartSnake.get(i-1).getrBody())
                    ||arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrBottom().intersect(arrPartSnake.get(i+1).getrBody())){
                arrPartSnake.get(i).setBm(bm_body_bottom_right);
            }else if(arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i+1).getrBody())
                    ||arrPartSnake.get(i).getrLeft().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrRight().intersect(arrPartSnake.get(i-1).getrBody())){
                arrPartSnake.get(i).setBm(bm_body_horizental);
            }else if(arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i-1).getrBody())
                    &&arrPartSnake.get(i).getrBottom().intersect(arrPartSnake.get(i+1).getrBody())
                    ||arrPartSnake.get(i).getrTop().intersect(arrPartSnake.get(i+1).getrBody())
                    &&arrPartSnake.get(i).getrBottom().intersect(arrPartSnake.get(i-1).getrBody())){
                arrPartSnake.get(i).setBm(bm_body_vertical);
            }else{
                if(move_right){
                    arrPartSnake.get(i).setBm(bm_body_horizental);
                }else if(move_Bottom){
                    arrPartSnake.get(i).setBm(bm_body_vertical);
                }else if(move_top){
                    arrPartSnake.get(i).setBm(bm_body_vertical);
                }else{
                    arrPartSnake.get(i).setBm(bm_body_horizental);
                }
            }
        }
        if(arrPartSnake.get(lenght-1).getrRight().intersect(arrPartSnake.get(lenght-2).getrBody())){
            arrPartSnake.get(lenght-1).setBm(bm_tail_right);
        }else if(arrPartSnake.get(lenght-1).getrLeft().intersect(arrPartSnake.get(lenght-2).getrBody())){
            arrPartSnake.get(lenght-1).setBm(bm_tail_left);
        }else if(arrPartSnake.get(lenght-1).getrBottom().intersect(arrPartSnake.get(lenght-2).getrBody())){
            arrPartSnake.get(lenght-1).setBm(bm_tail_down);
        }else{
            arrPartSnake.get(lenght-1).setBm(bm_tail_up);
        }
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < lenght; i++) {
            canvas.drawBitmap(arrPartSnake.get(i).getBm(), arrPartSnake.get(i).getX(), arrPartSnake.get(i).getY(), null);
        }
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public Bitmap getBm_head_down() {
        return bm_head_down;
    }

    public void setBm_head_down(Bitmap bm_head_down) {
        this.bm_head_down = bm_head_down;
    }

    public Bitmap getBm_head_left() {
        return bm_head_left;
    }

    public void setBm_head_left(Bitmap bm_head_left) {
        this.bm_head_left = bm_head_left;
    }

    public Bitmap getBm_head_right() {
        return bm_head_right;
    }

    public void setBm_head_right(Bitmap bm_head_right) {
        this.bm_head_right = bm_head_right;
    }

    public Bitmap getBm_head_up() {
        return bm_head_up;
    }

    public void setBm_head_up(Bitmap bm_head_up) {
        this.bm_head_up = bm_head_up;
    }

    public Bitmap getBm_body_vertical() {
        return bm_body_vertical;
    }

    public void setBm_body_vertical(Bitmap bm_body_vertical) {
        this.bm_body_vertical = bm_body_vertical;
    }

    public Bitmap getBm_body_horizontal() {
        return bm_body_horizental;
    }

    public void setBm_body_horizontal(Bitmap bm_body_horizontal) {
        this.bm_body_horizental = bm_body_horizontal;
    }

    public Bitmap getBm_body_bottom_left() {
        return bm_body_bottom_left;
    }

    public void setBm_body_bottom_left(Bitmap bm_body_bottom_left) {
        this.bm_body_bottom_left = bm_body_bottom_left;
    }

    public Bitmap getBm_body_bottom_right() {
        return bm_body_bottom_right;
    }

    public void setBm_body_bottom_right(Bitmap bm_body_bottom_right) {
        this.bm_body_bottom_right = bm_body_bottom_right;
    }

    public Bitmap getBm_body_top_left() {
        return bm_body_top_left;
    }

    public void setBm_body_top_left(Bitmap bm_body_top_left) {
        this.bm_body_top_left = bm_body_top_left;
    }

    public Bitmap getBm_body_top_right() {
        return bm_body_top_right;
    }

    public void setBm_body_top_right(Bitmap bm_body_top_right) {
        this.bm_body_top_right = bm_body_top_right;
    }

    public Bitmap getBm_tail_up() {
        return bm_tail_up;
    }

    public void setBm_tail_up(Bitmap bm_tail_up) {
        this.bm_tail_up = bm_tail_up;
    }

    public Bitmap getBm_tail_down() {
        return bm_tail_down;
    }

    public void setBm_tail_down(Bitmap bm_tail_down) {
        this.bm_tail_down = bm_tail_down;
    }

    public Bitmap getBm_tail_right() {
        return bm_tail_right;
    }

    public void setBm_tail_right(Bitmap bm_tail_right) {
        this.bm_tail_right = bm_tail_right;
    }

    public Bitmap getBm_tail_left() {
        return bm_tail_left;
    }

    public void setBm_tail_left(Bitmap bm_tail_left) {
        this.bm_tail_left = bm_tail_left;
    }

    public ArrayList<PartSnake> getArrPartSnake() {
        return arrPartSnake;
    }

    public void setArrPartSnake(ArrayList<PartSnake> arrPartSnake) {
        this.arrPartSnake = arrPartSnake;
    }

    public int getLength() {
        return lenght;
    }

    public void setLength(int length) {
        this.lenght = length;
    }

    public boolean isMove_left() {
        return move_left;
    }

    public void setMove_left(boolean move_left) {
        this.setup();
        this.move_left = move_left;
    }

    public boolean isMove_right() {
        return move_right;
    }

    public void setMove_right(boolean move_right) {
        this.setup();
        this.move_right = move_right;
    }

    public boolean isMove_up() {
        return move_top;
    }

    public void setMove_up(boolean move_up) {
        this.setup();
        this.move_top = move_up;
    }

    public boolean isMove_down() {
        return move_Bottom;
    }

    public void setMove_down(boolean move_down) {
        this.setup();
        this.move_Bottom = move_down;
    }

    public void setup(){
        this.move_right = false;
        this.move_Bottom = false;
        this.move_left = false;
        this.move_top = false;
    }

    public void addPart() {
        PartSnake p = this.arrPartSnake.get(lenght-1);
        this.lenght += 1;
        if(p.getBm()==bm_tail_right){
            this.arrPartSnake.add(new PartSnake(bm_tail_right, p.getX()-GameView.sizeofMap, p.getY()));
        }else if(p.getBm()==bm_tail_left){
            this.arrPartSnake.add(new PartSnake(bm_tail_left, p.getX()+GameView.sizeofMap, p.getY()));
        }else if(p.getBm()==bm_tail_up){
            this.arrPartSnake.add(new PartSnake(bm_tail_up, p.getX(), p.getY()+GameView.sizeofMap));
        }else if(p.getBm()==bm_tail_down){
            this.arrPartSnake.add(new PartSnake(bm_tail_up, p.getX(), p.getY()-GameView.sizeofMap));
        }
    }
}