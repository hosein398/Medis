package com.holding.medis.components.spinkit;


import com.holding.medis.components.spinkit.sprite.Sprite;
import com.holding.medis.components.spinkit.style.ChasingDots;
import com.holding.medis.components.spinkit.style.Circle;
import com.holding.medis.components.spinkit.style.CubeGrid;
import com.holding.medis.components.spinkit.style.DoubleBounce;
import com.holding.medis.components.spinkit.style.FadingCircle;
import com.holding.medis.components.spinkit.style.FoldingCube;
import com.holding.medis.components.spinkit.style.MultiplePulse;
import com.holding.medis.components.spinkit.style.MultiplePulseRing;
import com.holding.medis.components.spinkit.style.Pulse;
import com.holding.medis.components.spinkit.style.PulseRing;
import com.holding.medis.components.spinkit.style.RotatingCircle;
import com.holding.medis.components.spinkit.style.RotatingPlane;
import com.holding.medis.components.spinkit.style.ThreeBounce;
import com.holding.medis.components.spinkit.style.WanderingCubes;
import com.holding.medis.components.spinkit.style.Wave;

public class SpriteFactory {

    public static Sprite create(Style style) {
        Sprite sprite = null;
        switch (style) {
            case ROTATING_PLANE:
                sprite = new RotatingPlane();
                break;
            case DOUBLE_BOUNCE:
                sprite = new DoubleBounce();
                break;
            case WAVE:
                sprite = new Wave();
                break;
            case WANDERING_CUBES:
                sprite = new WanderingCubes();
                break;
            case PULSE:
                sprite = new Pulse();
                break;
            case CHASING_DOTS:
                sprite = new ChasingDots();
                break;
            case THREE_BOUNCE:
                sprite = new ThreeBounce();
                break;
            case CIRCLE:
                sprite = new Circle();
                break;
            case CUBE_GRID:
                sprite = new CubeGrid();
                break;
            case FADING_CIRCLE:
                sprite = new FadingCircle();
                break;
            case FOLDING_CUBE:
                sprite = new FoldingCube();
                break;
            case ROTATING_CIRCLE:
                sprite = new RotatingCircle();
                break;
            case MULTIPLE_PULSE:
                sprite = new MultiplePulse();
                break;
            case PULSE_RING:
                sprite = new PulseRing();
                break;
            case MULTIPLE_PULSE_RING:
                sprite = new MultiplePulseRing();
                break;
            default:
                break;
        }
        return sprite;
    }
}
