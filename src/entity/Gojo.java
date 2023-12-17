package entity;

import module.Symbols;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Gojo extends Entity {
    public final int X_COORDINATE = 50;
    public final int Y_COORDINATE = 110;
    public final int WIDTH = 500;
    public final int HEIGHT = 600;
    public BufferedImage heartFullSprite;
    public BufferedImage heartNullSprite;
    public Stack<Symbols> spells = new Stack<>(); // stack chứa các hanh động

    public Gojo(int health) {
        super(3);
        this.attack = 50;
        this.idleSprite = new BufferedImage[MAX_SPRITE_NUM];
        this.attackSprite = new BufferedImage[MAX_SPRITE_NUM];
        this.hurtSprite = new BufferedImage[MAX_SPRITE_NUM];
        loadSprites(); // load các sprite
    }

    private void loadSprites() {
        try {
            for (int i = 0; i < MAX_SPRITE_NUM; i++) {
                idleSprite[i] = scaleImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("resource/images/gojo_idle" + (i + 1) + ".png")), WIDTH, HEIGHT);
                attackSprite[i] = scaleImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("resource/images/gojo_attack" + (i + 1) + ".png")), WIDTH, HEIGHT);
                hurtSprite[i] = scaleImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("resource/images/gojo_hurt" + (i + 1) + ".png")), WIDTH, HEIGHT);
            }
            heartFullSprite = scaleImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("resource/images/heart_full.png")), 200, 200);
            heartNullSprite = scaleImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("resource/images/heart_null.png")), 200, 200);
        } catch (Exception e) { // bắt lỗi
            e.printStackTrace(); // in ra lỗi
        }
    }

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
        /**
         * vẽ các trái tim thể hiện máu
         */
        int i = 1;
        while (i < health) {
            g2d.drawImage(heartFullSprite, 150 * i - 150, 0, null);
            i++;
        }
        while (i <= 3) {
            g2d.drawImage(heartNullSprite, 150 * i - 150, 0, null);
            i++;
        }
    }

    public void setState(int state) { // set trạng thái
        this.state = state;
    }

    public int getHealth() { // lấy máu
        return health;
    }

    public Stack<Symbols> getSpells() { // lấy stack chứa các hành động
        return spells;
    }

    public void castSpell(String symbolName) {
        spells.push(new Symbols(Integer.parseInt(symbolName.substring(6))));
    }

    /**
     * tan cong rong, set trạng thái của Gojo thành tấn công
     */
    public void attack(int correct, Dragon dragon) {
        setState(ATTACK); // đổi trạng thái của Gojo thành tấn công
        dragon.takeDamage(correct); // gọi hàm takeDamage() của rồng
    }

    /**
     * thay đổi trang thái bi tan cong
     * nhận sát thương từ rồng
     */
    public void takeDamage() {
        this.setState(HURT); // đổi trạng thái của rồng thành bị đánh
        this.health--; // giảm máu của rồng
    }
}
