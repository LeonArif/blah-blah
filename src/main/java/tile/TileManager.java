package tile;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import entities.Bed;
import entities.Door;
import entities.Farm;
import entities.FarmManager;
import entities.Furniture;
import entities.Player;
import entities.Stove;
import entities.TV;
import gui.GamePanel;
import map.Point;


public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public char[][] mapTiles;
    BufferedImage houseImage;
    BufferedImage shipImage;
    BufferedImage pondImage;
    BufferedImage doorImage;
    BufferedImage bedImage;
    BufferedImage tvImage;
    BufferedImage stoveImage;

    public TileManager(GamePanel gp, Player player) {
        this.gp = gp;
        Farm farm = FarmManager.getFarmByName(player.getFarm());
        tile = new Tile[100];
        if (player.getPlayerLocation().getName().equals("Farm")) {
            mapTiles = farm.getFarmMap().getFarmMapDisplay();
        }
        else if (player.getPlayerLocation().getName().equals("House")) {
            mapTiles = gp.houseMap.getHouseMapDisplay();
        }
        else if (player.getPlayerLocation().getName().equals("Ocean")) {
            mapTiles = gp.ocean.getOceanDisplay();
        }
        else if (player.getPlayerLocation().getName().equals("ForestRiver")) {
            mapTiles = gp.forestRiver.getForestRiverDisplay();
        }
        else if (player.getPlayerLocation().getName().equals("MountainLake")) {
            mapTiles = gp.mountainLake.getMountainLakeDisplay();
        }
        else if (player.getPlayerLocation().getName().equals("Store")) {
            mapTiles = gp.store.getStoreDisplay();
        }
        getTileImage();
        loadHouseImage();
        loadShippingImage();
        loadPondImage();
        loadBedImage();
        loadDoorImage();
        loadStoveImage();
        loadTvImage();
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tileijo.png"));
            tile[0].collision = false;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tileijo.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tileijo.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tilled.png"));
            tile[3].collision = false;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/planted.png"));
            tile[4].collision = false;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/air.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/floor.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/floor.png"));
            tile[7].collision = false;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/upin.jpg"));
            tile[9].collision = false;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tilekuning.png"));
            tile[10].collision = false;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/floor.png"));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/floor.png"));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/floor.png"));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/penghubungair.png"));
            tile[14].collision = false;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/airpantai.png"));
            tile[15].collision = true;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/penghubungairpantai.png"));
            tile[16].collision = false;

            tile[17] = new Tile();
            tile[17].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tilepantai.png"));
            tile[17].collision = false;

            tile[18] = new Tile();
            tile[18].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tembokstore.png"));
            tile[18].collision = true;

            tile[19] = new Tile();
            tile[19].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/watered.png"));
            tile[19].collision = false;

            tile[20] = new Tile();
            tile[20].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/tileijo.png"));
            tile[20].collision = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int playerWorldX = gp.player.getPlayerLocation().getCurrentPoint().getX();
        int playerWorldY = gp.player.getPlayerLocation().getCurrentPoint().getY();
        
        int leftBound = playerWorldX - gp.player.screenX;
        int rightBound = playerWorldX + gp.player.screenX;
        int topBound = playerWorldY - gp.player.screenY;
        int bottomBound = playerWorldY + gp.player.screenY;
        
        for (int row = 0; row < gp.maxWorldRow; row++) {
            for (int col = 0; col < gp.maxWorldCol; col++) {
                int worldX = col * gp.tileSize;
                int worldY = row * gp.tileSize;
                
                if (worldX + gp.tileSize > leftBound && 
                    worldX - gp.tileSize < rightBound &&
                    worldY + gp.tileSize > topBound &&
                    worldY - gp.tileSize < bottomBound) {
                    
                    int screenX = worldX - playerWorldX + gp.player.screenX;
                    int screenY = worldY - playerWorldY + gp.player.screenY;

                    char tileChar = mapTiles[row][col];
                    int tileIndex = getTileIndex(tileChar);
                    if (tile[tileIndex] == null) {
                        System.out.println("tile[" + tileIndex + "] is null for char '" + tileChar + "'");
                        continue;
                    }
                    g2.drawImage(tile[tileIndex].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
            }
        }
        if (gp.player.getPlayerLocation().getName().equals("Farm")) {
            drawHouseImage(g2);
            drawShipping(g2);
            drawPond(g2);
        }
        else if(gp.player.getPlayerLocation().getName().equals("House")) {
            drawAllFurniture(g2);
            drawDoor(g2);
        }
    }

    public int getTileIndex(char c) {
        return switch (c) {
            case '.' -> 0;
            case 'h' -> 1;
            case 's' -> 2;
            case 't' -> 3;
            case 'l' -> 4;
            case 'o' -> 5;
            case 'D' -> 6;
            case ',' -> 7;
            case 'W' -> 8;
            case '`' -> 9;
            case 'm' -> 10;
            case 'B' -> 11;
            case 'S' -> 12;
            case 'T' -> 13;
            case 'q' -> 14;
            case 'Q' -> 15;
            case 'L' -> 16;
            case 'U' -> 17;
            case ']' -> 18;
            case 'w' -> 19;
            case 'O' -> 20;
            default -> 0;
        };
    }
    private void loadHouseImage() {
        try {
            houseImage = ImageIO.read(getClass().getResourceAsStream("/res/tiles/House3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadShippingImage() {
        try {
            shipImage = ImageIO.read(getClass().getResourceAsStream("/res/tiles/shipping-bin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPondImage() {
        try {
            pondImage = ImageIO.read(getClass().getResourceAsStream("/res/tiles/pond.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBedImage() {
        try {
            bedImage = ImageIO.read(getClass().getResourceAsStream("/res/items/furniture/bed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadStoveImage() {
        try {
            stoveImage = ImageIO.read(getClass().getResourceAsStream("/res/items/furniture/stove.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDoorImage() {
        try {
            doorImage = ImageIO.read(getClass().getResourceAsStream("/res/items/furniture/door.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadTvImage() {
        try {
            tvImage = ImageIO.read(getClass().getResourceAsStream("/res/items/furniture/tv.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void drawHouseImage(Graphics2D g2) {
        Point houseStart = gp.farm.getFarmMap().houseStartPoint;
        int tileSize = gp.tileSize;
        if (houseStart != null && houseImage != null) {
            int drawX = houseStart.getX() * tileSize;
            int drawY = houseStart.getY() * tileSize;

            int screenX = drawX - gp.player.getPlayerLocation().getCurrentPoint().getX() + gp.player.screenX;
            int screenY = drawY - gp.player.getPlayerLocation().getCurrentPoint().getY() + gp.player.screenY;

            g2.drawImage(houseImage, screenX, screenY, tileSize * 6, tileSize * 6, null);
        }
    }
    private void drawShipping(Graphics2D g2) {
        Point shipStart = gp.farm.getFarmMap().shippingBinPoint;
        int tileSize = gp.tileSize;
        if (shipStart != null && houseImage != null) {
            int drawX = shipStart.getX() * tileSize;
            int drawY = shipStart.getY() * tileSize;

            int screenX = drawX - gp.player.getPlayerLocation().getCurrentPoint().getX() + gp.player.screenX;
            int screenY = drawY - gp.player.getPlayerLocation().getCurrentPoint().getY() + gp.player.screenY;

            g2.drawImage(shipImage, screenX, screenY, tileSize * 3, tileSize * 2, null);
        }
    }

    private void drawPond(Graphics2D g2) {
        Point pondStart = gp.farm.getFarmMap().pondStartPoint;
        int tileSize = gp.tileSize;
        if (pondStart != null && houseImage != null) {
            int drawX = pondStart.getX() * tileSize;
            int drawY = pondStart.getY() * tileSize;

            int screenX = drawX - gp.player.getPlayerLocation().getCurrentPoint().getX() + gp.player.screenX;
            int screenY = drawY - gp.player.getPlayerLocation().getCurrentPoint().getY() + gp.player.screenY;

            g2.drawImage(pondImage, screenX, screenY, tileSize * 4, tileSize * 3, null);
        }
    }
private void drawAllFurniture(Graphics2D g2) {
    int tileSize = gp.tileSize;
    for (Map.Entry<Furniture, List<Point>> entry : gp.houseMap.getFurnitureLocation().entrySet()) {
        Furniture f = entry.getKey();
        BufferedImage img = null;
        int width = tileSize, height = tileSize;

        if (f instanceof Bed) {
            img = bedImage;
            width = tileSize * f.getFurnitureSizeX();
            height = tileSize * f.getFurnitureSizeY();
        } else if (f instanceof Stove) {
            img = stoveImage;
        } else if (f instanceof TV) {
            img = tvImage;
        } else if (f instanceof Door) {
            img = doorImage;
            width = tileSize;
        }
        for (Point start : entry.getValue()) {
            int drawX = start.getX() * tileSize;
            int drawY = start.getY() * tileSize;
            int screenX = drawX - gp.player.getPlayerLocation().getCurrentPoint().getX() + gp.player.screenX;
            int screenY = drawY - gp.player.getPlayerLocation().getCurrentPoint().getY() + gp.player.screenY;
            if (img != null) {
                g2.drawImage(img, screenX, screenY, width, height, null);
            }
        }
    }
}

    private void drawDoor(Graphics2D g2) {
        // Ambil Door
        Point doorStart = gp.houseMap.getFurnitureStartPoint(Door.class);
        int tileSize = gp.tileSize;
        if (doorStart != null && doorImage != null) {
            int drawX = (doorStart.getX()-1) * tileSize;
            int drawY = doorStart.getY() * tileSize;

            int screenX = drawX - gp.player.getPlayerLocation().getCurrentPoint().getX() + gp.player.screenX;
            int screenY = drawY - gp.player.getPlayerLocation().getCurrentPoint().getY() + gp.player.screenY;

            g2.drawImage(doorImage, screenX, screenY, tileSize * 2, tileSize, null); // gambar pintu lebar 2 tile
        }
    }
}