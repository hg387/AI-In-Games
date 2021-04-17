package controllers;

import engine.Car;
import engine.Game;
import engine.GameObject;
import utilities.OutputFiltering;
import utilities.Vector;

import java.util.ArrayList;

public class ArriveController extends Controller {
    public GameObject target;
    public double targetRadius;
    public double slowRadius;
    public double maxAcceleration = 6.0;

    public ArriveController(GameObject target, double targetRadius, double slowRadius){
        this.target = target;
        this.targetRadius = targetRadius;
        this.slowRadius = slowRadius;
    }

    @Override
    public void update(Car subject, Game game, double delta_t, double[] controlVariables) {
        controlVariables[VARIABLE_STEERING] = 0;
        controlVariables[VARIABLE_THROTTLE] = 0;
        controlVariables[VARIABLE_BRAKE] = 0;

        Vector D = (new Vector(this.target.getX(), 0, this.target.getY()))
                .subtract(new Vector(subject.getX(), 0, subject.getY()));
        double Length = D.magnitude();

        Vector A = null;
        double targetSpeed;
        Vector targetVelocity;
        if (Length < targetRadius)
        {
            A = new Vector(0, 0, 0);
        }
        else {
            if (Length > slowRadius) {
                targetSpeed = subject.m_max_velocity;
                //System.out.println("Distance " + Length +" greater than slow Radius, target speed max");
            } else {
                targetSpeed = (subject.m_max_velocity * Length) / (this.slowRadius);
                //System.out.println("Distance " + Length +" smaller than slow Radius, target speed " + targetSpeed);
            }

            targetVelocity = D.unitVector().multiply(targetSpeed);
            A = targetVelocity
                    .subtract(new Vector(Math.cos(subject.getAngle()), 0, Math.sin(subject.getAngle()))
                            .multiply(subject.getSpeed()))
                    .divide(delta_t);
        }

        if (A.magnitude() > maxAcceleration) A = A.unitVector().multiply(maxAcceleration);

        // Output Filtering
        new OutputFiltering().moveCar(subject, A, controlVariables);
    }
}
