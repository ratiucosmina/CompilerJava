package Model;

import javafx.event.Event;
import javafx.event.EventType;
import sample.ControllerGUI;


public class MyEvent extends Event {
    public MyEvent(){super(new ControllerGUI(),null, new EventType("MyEvent"));};
}
