package controllers;

import engine.Car;
import engine.Game;
import engine.GameObject;
import utilities.OutputFiltering;
import utilities.Vector;

import java.io.IOException;

public class WallAvoidanceController extends Controller {
    public GameObject target;
    public double maxAcceleration = 6.0;

    public WallAvoidanceController(GameObject target){
        this.target = target;
    }

    @Override
    public void update(Car subject, Game game, double delta_t, double[] controlVariables) {
        controlVariables[VARIABLE_STEERING] = 0;
        controlVariables[VARIABLE_THROTTLE] = 0;
        controlVariables[VARIABLE_BRAKE] = 0;

        try {
            OutputFiltering.RayCast leftCast = new OutputFiltering().new RayCast(subject.m_img, 50, -Math.PI / 4);
            OutputFiltering.RayCast rightCast = new OutputFiltering().new RayCast(subject.m_img, 50, Math.PI / 4);
            OutputFiltering.RayCast frontCast = new OutputFiltering().new RayCast(subject.m_img, 50, 0);

            Vector tmpTarget = new Vector(this.target.getX(), 0, this.target.getY());
            if (leftCast.cast(game, subject)) {
                //System.out.println("left wall");
                tmpTarget = leftCast.avoidCollision(subject);
            }
            else if (rightCast.cast(game, subject)){
                //System.out.println("right wall");
                tmpTarget = rightCast.avoidCollision(subject);
            }
            else if (frontCast.cast(game, subject)){
                //System.out.println("front wall");
                tmpTarget = frontCast.avoidCollision(subject);
            }

            Vector D = tmpTarget.subtract(new Vector(subject.getX(), 0, subject.getY()));
            Vector ND = D.unitVector();
            Vector A = ND.multiply(maxAcceleration);

            // Output Filtering
            new OutputFiltering().moveCar(subject, A, controlVariables);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
