
public class Vector2D {

	public double x;
	public double y;
	
	public Vector2D(double X, double Y){
		x = X;
		y = Y;
	}
	
	public Vector2D vectorAdd(Vector2D B){
		x = x + B.x;
		y = y + B.y;
		return this;
	}
	
	public Vector2D vectorSub(Vector2D B){
		x = x - B.x;
		y = y - B.y;
		return this;
	}
	
	public Vector2D vectorMulti(double B){
		x = x * B;
		y = y * B;
		return this;
	}
	
	public Vector2D vectorDivide(double B){
		x = x / B;
		y = y / B;
		return this;
	}
}
