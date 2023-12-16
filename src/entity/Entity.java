package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int health; // máu
    public int attack; // sát thương
    public int spriteNum; // số sprite
    public int spriteTime = 1; // thời gian hiển thị sprite
    public int state = 0;   // 0 - đứng yên, 1 - tấn công , 2 - bị đánh
    public final int IDLE = 0; // trạng thái đứng yên
    public final int ATTACK = 1; // trạng thái tấn công
    public final int HURT = 2; // trạng thái bị đánh
    public final int SPRITE_INTERVAL = 3; // thời gian chuyển đổi giữa các sprite
    public final int MAX_SPRITE_NUM = 3; // số sprite tối đa = 3
    public BufferedImage[] ideSprites; //mảng chứa sprite khi đứng yên
    public BufferedImage[] attackSprites; // mảng chứa sprite khi tấn công
    public BufferedImage[] hurtSprites; // mảng chứa sprite khi bị đánh
    public BufferedImage currentSprite; // sprite hiện tại

    public Entity(int health) {
        this.health = health;
    }

    // thay đổi kích thước của Image
    public BufferedImage scaleImage(BufferedImage image, int width, int height) {
        // tạo đối tượng BufferedImage
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // lấy đối tượng Graphics2D để vẽ lại ảnh với kích thước mới
        Graphics2D g2d = resizedImage.createGraphics();
        // vẽ lại ảnh với kích thước mới
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();
        //        resizedImage.getGraphics().drawImage(image, 0, 0, width, height, null);
        return resizedImage;
    }
}

