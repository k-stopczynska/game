package org.entity;

import org.main.Panel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.main.KeyHandler;

public class Player extends Entity {
    Panel panel;
    KeyHandler keyHandler;

    public Player(Panel panel, KeyHandler keyHandler) {
        this.panel = panel;
        this.keyHandler = keyHandler;
        this.setDefault();
        this.getPlayerSprite();
    }

    public void setDefault() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerSprite() {
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_down_2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_left_2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/player_right_2.png"));
        } catch(IOException e) {
            System.out.println("There was an exception while loading images");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("There was another exception");
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.upPressed) {
            direction = "up";
            y -= speed;
            move();
        }
        if (keyHandler.downPressed) {
            direction = "down";
            y += speed;
            move();
        }
        if (keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
            move();
        }
        if (keyHandler.rightPressed) {
            direction = "right";
            x += speed;
            move();
        }
    

    }
    
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        graphics2D.drawImage(image, x, y, panel.TILE_SIZE, panel.TILE_SIZE, null);
    }
    
    public void move() {
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum = (spriteNum == 1) ? 2 : 1;
            spriteCounter = 0;
        }
    }
}
