package view;

import controller.Sorting.Algorithm;
import unit.Resolution;
import unit.*;

import java.util.List;

public abstract class AbstractFrame implements Window {

    protected final List<Speed> speeds;
    protected final List<Resolution> resolutions;
    protected List<Algorithm> algorithms;

    public AbstractFrame(List<Speed> speeds, List<Resolution> resolutions) {
        this.speeds = speeds;
        this.resolutions = resolutions;
    }

    public void setAlgorithms(List<Algorithm> algorithms) {
        this.algorithms = algorithms;
    }

}