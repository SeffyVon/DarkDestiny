package com.darkdensity.maprender;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import com.darkdensity.setting.Config;
import com.darkdensity.util.ImageLoader;

public class TileMap {

	private Image[][] tiles;
	private Image background;

	// width and height of tile, pixels
	private static final int ROW_NUM = 15;// 0-14 15rows
	private static final int COLUMN_NUM = 19;// 0-18 19 columns

	// width and height of tile, pixels
	private static final int TILE_WIDTH = 256;
	private static final int TILE_HEIGHT = 256;

	// width and height of the map, pixels
	public static int mapWidthPx;
	public static int mapHeightPx;

	// render start posistion
	public int iRenderX = 0;
	public int iRenderY = 0;

	// initialize the tile map
	public TileMap() {
		//tiles = new Image[COLUMN_NUM][ROW_NUM];
		mapWidthPx = COLUMN_NUM * TILE_WIDTH;
		mapHeightPx = ROW_NUM * TILE_HEIGHT;
	}

	// draw the map to the screen
	public void redraw(Graphics2D g, int screenWidth, int screenHeight) {

		int offsetX = iRenderX % TILE_WIDTH;
		int offsetY = iRenderY % TILE_HEIGHT;

		// draw the visible tiles
		int firstTileX = pixel2TileX(iRenderX);
		System.out.println(iRenderX);
		int lastTileX = firstTileX + pixel2TileX(screenWidth) + 1;

		int firstTileY = pixel2TileY(iRenderY);
		int lastTileY = firstTileY + pixel2TileY(screenHeight) + 1;
		//System.out.println(iRenderX);
		//System.out.println(iRenderY);
		Image image = ImageLoader
				.loadImage(Config.MINI_MAP_PATH + "BigMap.jpg");
		g.drawImage(image, 0 - iRenderX, 0 - iRenderY, null);

		// //������������
		// for (int y=firstTileY; y<lastTileY; y++) {
		// for (int x=firstTileX; x <= lastTileX; x++) {
		//
		// // Image image = map.getTile(x, y);
		// //
		// // if (image != null) {
		// // g.drawImage(image,
		// // (x-firstTileX)*TILE_WIDTH - offsetX,
		// // (y-firstTileY)*TILE_HEIGHT - offsetY,
		// // null);
		// // }else{
		// //���������������������������tile
		// // x column y row
		// Image image = ImageLoader.loadImage( Config.MAP_PATH +"05_" + y + "_"
		// + x + ".jpg");
		// g.drawImage(image,
		// (x-firstTileX)*TILE_WIDTH - offsetX,
		// (y-firstTileY)*TILE_HEIGHT - offsetY,
		// null);
		// // }
		// }
		// }
	}

	public static int tile2PixelX(int tilesNum) {
		return tilesNum * TILE_WIDTH;
	}

	public static int tile2PixelY(int tilesNum) {
		return tilesNum * TILE_HEIGHT;
	}

	public static int pixel2TileX(int pixels) {
		return pixels / TILE_WIDTH;
	}

	public static int pixel2TileY(int pixels) {
		return pixels / TILE_HEIGHT;
	}

	public static int getMapWidthPx() {
		return mapWidthPx;
	}

	public static int getMapHeightPx() {
		return mapHeightPx;
	}

	public int getMapWidthTile() {
		return tiles.length;
	}

	public int getMapHeightTile() {
		return tiles[0].length;
	}

}
