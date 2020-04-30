package hud;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Coord;
import player.Player;

public class CoinDraw {

	int m_x, m_y, m_width, m_height;
	Image coinIco;
	Player m_player;
	private String COIN_ICO_SPRITE = "resources/HUD/AnimatedCoin.png";
	private Coord moneyC;
	Font font;
	protected int m_imageIndex;
	protected long m_imageElapsed;
	protected Image[] m_images;

	public CoinDraw(int x, int y, int w, int h, Player p) {
		m_x = x;
		m_y = y;
		m_width = w;
		m_height = h;
		m_player = p;
		try {
			m_images = loadSprite(COIN_ICO_SPRITE, 1, 6);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		coinIco = m_images[0];
		font = new Font("Georgia", Font.BOLD, 20);
		moneyC = new Coord(m_x + coinIco.getHeight(null) * 2, m_y + m_height / 2 - font.getSize());

	}

	public void paint(Graphics g) {
		g.setFont(font);
		g.drawImage(coinIco, m_x, m_y, null);
		g.drawString(String.valueOf(m_player.getMoney()), moneyC.X(), moneyC.Y());
	}

	public void tick(long elapsed) {
		m_imageElapsed += elapsed;
		if (m_imageElapsed > 200) {
			m_imageElapsed = 0;
			m_imageIndex = (m_imageIndex + 1) % m_images.length;
			coinIco = m_images[m_imageIndex];
		}
	}
	
	public Image[] loadSprite(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			Image[] images = new Image[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
					images[(i * ncols) + j] = images[(i * ncols) + j].getScaledInstance(m_width / 3, m_height / 2, java.awt.Image.SCALE_SMOOTH);
				}
			}
			return images;
		}
		return null;
	}
}
