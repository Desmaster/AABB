package nl.tdegroot.aabb;

public class Box {

	public Vector2i position, size;

	public Box(int x, int y, int width, int height) {
		position = new Vector2i(x, y);
		size = new Vector2i(width, height);
	}

	/*public static boolean collides(Box a, Box b) {
		if (a.position.x + a.size.x > b.position.x - b.size.x) {
			if (a.position.y + a.size.y > b.position.y - b.size.y) {
				if (a.position.x - a.size.x < b.position.x + b.size.x) {
					if (a.position.y - a.size.y < b.position.y + b.size.y) {
						return true;
					}
				}
			}
		}
		return false;
	}*/

	public static boolean collides(Box a, Box b) {
		if (abs(a.position.x - b.position.x) < a.size.x + b.size.x) {
			if (abs(a.position.y - b.position.y) < a.size.y + b.size.y) {
				return true;
			}
		}
		return false;
	}

	private static int abs(int i) {
		return i < 0 ? i * -1 : i;
	}

}
