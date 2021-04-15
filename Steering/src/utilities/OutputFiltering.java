package utilities;

import controllers.Controller;
import engine.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OutputFiltering {

    public class RayCast{

        public int measureLength;
        public double deflectAngle;
        BufferedImage m_img;
        public double collisionLength = 0;

        public RayCast(BufferedImage m_img, int measureLength, double deflectAngle) throws IOException {
            this.m_img = m_img;
            this.measureLength = measureLength;
            this.deflectAngle = deflectAngle;
        }

        public boolean cast(Game game, Car subject){
            for (double d = 1; d<=measureLength; d++) {
                // Move a point in the ray cast direction by a distance d
                // x2 = x1 + d*cosB and y2 = y1 + d*sinB
                double castX = subject.getX() + (d * Math.cos(subject.getAngle() + deflectAngle));
                double castY = subject.getY() + (d * Math.sin(subject.getAngle() + deflectAngle));

                RotatedRectangle imaginaryBox = new RotatedRectangle(castX, castY, m_img.getWidth() / 2, m_img.getHeight() / 2, (subject.getAngle() + deflectAngle));

                for (GameObject o : game.m_objects) {
                    if (o instanceof Obstacle && RotatedRectangle.RotRectsCollision(imaginaryBox, o.getCollisionBox())) {
                        //System.out.println("collision length is " + collisionLength);
                        collisionLength = d;
                        return true;
                    }
                }
            }
            return false;
        }

        public Vector avoidCollision(Car subject){
            if (deflectAngle != 0){
                return new Vector((subject.getX() + (collisionLength*Math.cos(subject.getAngle() + (-1*deflectAngle))))
                        , 0
                        , (subject.getY() + (collisionLength*Math.sin(subject.getAngle() + (-1*deflectAngle)))));
            }
            else{
                return new Vector((subject.getX() - collisionLength*Math.cos(subject.getAngle()))
                        , 0
                        , (subject.getY() - collisionLength*Math.sin(subject.getAngle() )));

            }
        }
    }

    public void moveCar(Car subject, Vector A, double controlVariables[]){

        double ProjectionOfA = (new Vector(Math.cos(subject.getAngle()), 0, Math.sin(subject.getAngle())))
                .dot(A);

        // Formula of rotating a vector
        // x2 = cosB x1 - sinB y1 (i.e for Pi/2; x2 = -y1)
        // y2 = sinB x1 + cosB y1 (i.e for Pi/2; y2 = x1)

        Vector turnRight = new Vector((-1*Math.sin(subject.getAngle())), 0, Math.cos(subject.getAngle()));
        double ProjectionOfRight = turnRight.dot(A);

        if (ProjectionOfA > 0) controlVariables[Controller.VARIABLE_THROTTLE] = 1;
        else if (ProjectionOfA <= -1) controlVariables[Controller.VARIABLE_BRAKE] = 1;

        if (ProjectionOfRight != 0) controlVariables[Controller.VARIABLE_STEERING] = ProjectionOfRight/3;
    }
}
