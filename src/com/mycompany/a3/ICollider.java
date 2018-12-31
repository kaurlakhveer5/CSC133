package com.mycompany.a3;

public interface ICollider {
	public boolean collidesWith(ICollider otherobject);
	public void handleCollision(ICollider otherObject);
}
