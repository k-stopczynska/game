package org.tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.main.Panel;

public class TileManager {
    Panel panel;
    Tile[] tile;
    int map[] [];

    public TileManager(Panel panel) {
        this.panel = panel;
        tile = new Tile[10];
        map = new int[panel.MAX_SCREEN_COL][panel.MAX_SCREEN_ROW];
        getTileImages();
    }

    public void loadMap() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int col = 0;
            int row = 0;
            while (col < panel.MAX_SCREEN_COL && row < panel.MAX_SCREEN_ROW) {
                String line = reader.readLine();
                while (col < panel.MAX_SCREEN_COL) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    map[col][row] = num;
                    col++;
                }
                if (col == panel.MAX_SCREEN_COL) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Exception while loading map");
            e.printStackTrace();
        }
    }

    public void getTileImages() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/water.png"));
        } catch (IOException ioe) {
            System.out.println("There was an exception while getting tile images");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("There was another exception");
            e.printStackTrace();
        }
    };

    public void draw(Graphics2D graphics2D) {
        this.loadMap();

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < panel.MAX_SCREEN_COL && row < panel.MAX_SCREEN_ROW) {
            graphics2D.drawImage(tile[map[col][row]].image, x, y, panel.TILE_SIZE, panel.TILE_SIZE, null);
            x += panel.TILE_SIZE;
            col++;
            if (col == panel.MAX_SCREEN_COL) {
                x = 0;
                col = 0;
                y += panel.TILE_SIZE;
                row++;
            }
        }
    }
};
