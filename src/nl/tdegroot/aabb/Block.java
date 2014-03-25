package nl.tdegroot.aabb;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

public class Block implements Comparable<Block> {

	public int x, y, xx, yy;
	private int width, height;
	private int speed = 2;
	private Color color;

	private Box collision;

	public Block(int x, int y, int color) {
		this.x = x;
		this.y = y;
		width = 80;
		height = 120;
		this.color = new Color((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));

		collision = new Box(x + width / 2, y + height / 2, width / 2, height / 2);

	}

	public void move(int xa, int ya, List<Block> blocks) {
		if (xa != 0 && ya != 0) {
			move(xa, 0, blocks);
			move(0, ya, blocks);
			return;
		}

		if (!collision(xa, ya, blocks)) {
			x += xa * speed;
			y += ya * speed;
		}
	}

	public boolean collision(int xa, int ya, List<Block> blocks) {
		collision.position.x = x + xa + width / 2;
		collision.position.y = y + ya + height / 2;
		for (int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			if (b == this) continue;
			if (Box.collides(collision, b.collision)) return true;
		}
		return false;
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	@Override
	public int compareTo(Block b) {
		return 0;
	}
}
