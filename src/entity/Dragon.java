package entity;

import module.Symbols;
import view.View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Dragon extends Entity {
    public int X_COORDINATE = 11 * View.GRID_WIDTH;
    public int Y_COORDINATE = 4 * View.GRID_HEIGHT;
    public int WIDTH = 5 * View.GRID_WIDTH;
    public int HEIGHT = 8 * View.GRID_HEIGHT;
    Stack<Symbols> weakness = new Stack<>(); // stack chứa các symbol

    public Dragon() {
        super(3); // gọi constructor của lớp cha
        attack = 1; // sát thương = 1
        this.attackSprite = new BufferedImage[MAX_SPRITE_NUM]; // khởi tạo mảng chứa sprite khi tấn công
        this.idleSprite = new BufferedImage[MAX_SPRITE_NUM]; // khởi tạo mảng chứa sprite khi đứng yên
        this.hurtSprite = new BufferedImage[MAX_SPRITE_NUM]; // khởi tạo mảng chứa sprite khi bị đánh
        loadSprites(); // load các sprite
    }

    /**
     * load các sprite từ file ảnh vào mảng chứa sprite tương ứng với trạng thái của rồng (đứng yên, tấn công, bị đánh)
     */
    private void loadSprites() {
        try {
            for (int i = 0; i < MAX_SPRITE_NUM; i++) {
                idleSprite[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resource/images/dragon_idle" + (i + 1) + ".png"));
                idleSprite[i] = scaleImage(idleSprite[i], WIDTH, HEIGHT); // thay đổi kích thước của sprite

                attackSprite[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resource/images/dragonattack" + (i + 1) + ".png"));
                attackSprite[i] = scaleImage(attackSprite[i], WIDTH, HEIGHT); // thay đổi kích thước của sprite

                hurtSprite[i] = ImageIO.read(getClass().getClassLoader().getResourceAsStream("resource/images/dragonhurt" + (i + 1) + ".png"));
                hurtSprite[i] = scaleImage(hurtSprite[i], WIDTH, HEIGHT); // thay đổi kích thước của sprite
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dựa vào trạng thái của rồng để vẽ sprite tương ứng
     */
    public void draw(Graphics2D g2d) {
        if (state == IDLE) { // nếu rồng đang đứng yên
            currentSprite = idleSprite[spriteNum]; // sprite hiện tại là sprite khi đứng yên
            if (spriteTime == SPRITE_INTERVAL) { // nếu thời gian hiển thị sprite = 3
                spriteNum++; // tăng số sprite
                spriteTime = 0; // reset thời gian hiển thị sprite
                if (spriteNum == MAX_SPRITE_NUM) { // nếu số sprite = 3
                    spriteNum = 0; // reset số sprite
                }
            }
        } else if (state == ATTACK) { // nếu rồng đang tấn công
            currentSprite = attackSprite[spriteNum]; // sprite hiện tại là sprite khi tấn công
            if (spriteTime == SPRITE_INTERVAL) { // nếu thời gian hiển thị sprite = 3
                spriteNum++; // tăng số sprite
                spriteTime = 0; // reset thời gian hiển thị sprite
                if (spriteNum == MAX_SPRITE_NUM) { // nếu số sprite = 3
                    spriteNum = 0; // reset số sprite
                }
            }
        } else if (state == HURT) { // nếu rồng bị đánh
            currentSprite = hurtSprite[spriteNum]; // sprite hiện tại là sprite khi bị đánh
            if (spriteTime == SPRITE_INTERVAL) { // nếu thời gian hiển thị sprite = 3
                spriteNum++; // tăng số sprite
                spriteTime = 0; // reset thời gian hiển thị sprite
                if (spriteNum == MAX_SPRITE_NUM) { // nếu số sprite = 3
                    spriteNum = 0; // reset số sprite
                }
            }
        }

        spriteTime++; // tăng thời gian hiển thị sprite
        if (spriteTime == SPRITE_INTERVAL) { // nếu thời gian hiển thị sprite = 3
            spriteNum++; // tăng số sprite
            spriteTime = 0; // reset thời gian hiển thị sprite
            if (spriteNum == MAX_SPRITE_NUM) { // nếu số sprite = 3
                spriteNum = 0; // reset số sprite
            }
        }
        g2d.drawImage(currentSprite, X_COORDINATE, Y_COORDINATE, null); // vẽ sprite hiện tại
    }

    public Stack<Symbols> getWeakness() {
        return weakness;
    }

    public void setState(int state) { // set trạng thái của rồng
        this.state = state;
    }

    public void revealWeakness() { // hiện các symbol
        weakness.clear(); // xóa các symbol cũ
        for (int i = 0; i < 5; i++)
            weakness.push(new Symbols((int) (Math.random() * 6) + 1));
    }

    /**
     * tấn công Gojo, set trạng thái của rồng là tấn công
     *
     * @param gojo
     */
    public void attack(Gojo gojo) {
        this.setState(ATTACK); // set trạng thái là tấn công
        gojo.takeDamage(); // gọi hàm takeDamage() của Gojo
    }

    /**
     * nhận sát thương
     *
     * @param damage
     */
    public void takeDamage(int damage) {
        health -= damage; // giảm máu
        this.setState(HURT); // set trạng thái là bị đánh
    }


}
