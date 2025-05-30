package entities;
import java.io.IOException;

import javax.imageio.ImageIO;
import map.*;

import gui.*;
import items.ItemManager;

public class Caroline extends NPC{
    public Caroline(GamePanel gp) {
        super("Caroline", "single", gp);
        direction = "down";
        speed = 1;

        getNPCImage();
        npcLocation = new Location("CarolineHome", new Point(2 * gp.tileSize, 3 * gp.tileSize));
        this.addLovedItem(ItemManager.getItem("Firewood"));
        this.addLovedItem(ItemManager.getItem("Coal"));
        this.addLikedItem(ItemManager.getItem("Potato"));
        this.addLikedItem(ItemManager.getItem("Wheat"));
        this.addHatedItem(ItemManager.getItem("Hot Pepper"));
    }

    public void getNPCImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/carolineatas1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/carolineatas2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/carolinebawah1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/carolinebawah2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/carolinekiri1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/carolinekiri2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/npc/carolinekanan1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/npc/carolinekanan2.png"));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
