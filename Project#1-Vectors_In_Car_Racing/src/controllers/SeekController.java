package controllers;

import engine.Car;
import engine.Game;
import engine.GameObject;
import utilities.OutputFiltering;
import utilities.Vector;

public class SeekController extends Controller {
    public GameObject target;
    public double maxAcceleration = 6.0;

    public SeekController(GameObject target){
        this.target = target;
    }

    @Override
    public void update(Car subject, Game game, double delta_t, double[] controlVariables) {
        controlVariables[VARIABLE_STEERING] = 0;
        controlVariables[VARIABLE_THROTTLE] = 0;
        controlVariables[VARIABLE_BRAKE] = 0;

        Vector D = (new Vector(this.target.getX(), 0, this.target.getY()))
                .subtract(new Vector(subject.getX(), 0, subject.getY()));

        Vector ND = D.unitVector();
        Vector A = ND.multiply(maxAcceleration);

        // Output Filtering
        new OutputFiltering().moveCar(subject, A, controlVariables);
    }
}
