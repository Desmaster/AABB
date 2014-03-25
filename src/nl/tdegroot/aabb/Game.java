package nl.tdegroot.aabb;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private boolean running = false;
	private Input input;

	private List<Block> blocks = new ArrayList<Block>();
	private Random random = new Random();

	public Game() {
		setPreferredSize(new Dimension(960, 540));
		input = new Input();
		addKeyListener(input);
	}

	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void run() {
		blocks.add(new Block(50, 50, 0xff00ff));

		for (int y = 0; y < 50; y++) {
			for (int x = 0; x < 50; x++) {
				blocks.add(new Block(25 + x * 25, 25 + y * 25, random.nextInt(0xff)));
			}
		}

//		blocks.add(new Block(250, 250, random.nextInt(0xff)));

		Arrays.binarySearch(blocks.toArray(), blocks.get(0));

		long lastTime = System.nanoTime();
		double ns = 1000000000.0 / 60.0;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				updates++;
				if (input.keys[KeyEvent.VK_ESCAPE]) running = false;
				update();
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + "ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}

	int time = 0;

	public void update() {
		time++;
		int xa = 0;
		int ya = 0;
		Block b = blocks.get(0);
		if (input.keys[KeyEvent.VK_UP]) ya--;
		if (input.keys[KeyEvent.VK_DOWN]) ya++;
		if (input.keys[KeyEvent.VK_LEFT]) xa--;
		if (input.keys[KeyEvent.VK_RIGHT]) xa++;
		b.move(xa, ya, blocks);

		for (int i = 0; i < blocks.size(); i++) {
			Block block = blocks.get(i);
			if (random.nextInt(40) == 0) {
				block.xx = random.nextInt(3) - 1;
				block.yy = random.nextInt(3) - 1;
			}
			//block.move(block.xx, block.yy, blocks);
		}

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).render(g);
		}
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame("AABB");
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.start();
	}

}
