package frontend.frontend.animation.turtle;

import java.util.List;
import frontend.turtlescreen.TurtleScreenController;

public class UpdateCommandableEvent extends TurtleEvent {

    private List<Integer> turtleIds;
    
    public UpdateCommandableEvent (TurtleScreenController control, List<Integer> turtleIds) {
        super(control);
        this.turtleIds = turtleIds;
    }

    @Override
    public double update (double dt) {
        control.updateCommandable(turtleIds);
        setFinishedTrue();
        return dt;
    }

}
