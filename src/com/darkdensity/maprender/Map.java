package com.darkdensity.maprender;

import java.awt.Graphics2D;
import java.awt.Image;

import com.darkdensity.setting.Config;
import com.darkdensity.util.ImageLoader;

public class Map {
	private Image[][] tiles;
	private Image background;

	// width and height of tile, pixels
	private static final int ROW_NUM = 15;// 0-14 15rows
	private static final int COLUMN_NUM = 19;// 0-18 19 columns

	// width and height of tile, pixels
	private static final int TILE_WIDTH = 256;
	private static final int TILE_HEIGHT = 256;

	// width and height of the map, pixels
	public int mapWidthPx;
	public int mapHeightPx;

	// render start posistion
//	public int iRenderX = 0;
//	public int iRenderY = 0;
//	public int offsetX;
//	public int offsetY ;
	
	public String mapFile; 
	Image image;

	// initialize the tile map
	public Map(String mapFile) {
		//tiles = new Image[COLUMN_NUM][ROW_NUM];
		this.mapFile = mapFile;
//		mapWidthPx = COLUMN_NUM * TILE_WIDTH;
//		mapHeightPx = ROW_NUM * TILE_HEIGHT;
		//image  = ImageLoader.loadImage(Config.MINI_MAP_PATH + mapFile + ".jpg");
		image  = ImageLoader.loadImage(Config.MINI_MAP_PATH + mapFile + ".jpg");
		mapWidthPx = image.getWidth(null);
		mapHeightPx = image.getHeight(null);

		
	}

	// draw the map to the screen
	public Image redraw(Graphics2D g, int screenWidth, int screenHeight) {

		
//		offsetX = iRenderX % TILE_WIDTH;
//		offsetY = iRenderY % TILE_HEIGHT;
//
//		// draw the visible tiles
//		int firstTileX = pixel2TileX(iRenderX);
//		int lastTileX = firstTileX + pixel2TileX(screenWidth) + 1;
//
//		int firstTileY = pixel2TileY(iRenderY);
//		int lastTileY = firstTileY + pixel2TileY(screenHeight) + 1;
//		//System.out.println(iRenderX);
//		//System.out.println(iRenderY);
		
		return image;
		//g.drawImage(ResourceManager.Constant.IMAGE_MAP_BG, 0 - iRenderX, 0 - iRenderY, null);
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

	public int getMapWidthPx() {
		return mapWidthPx;
	}

	public int getMapHeightPx() {
		return mapHeightPx;
	}

	public int getMapWidthTile() {
		return tiles.length;
	}

	public int getMapHeightTile() {
		return tiles[0].length;
	}
}