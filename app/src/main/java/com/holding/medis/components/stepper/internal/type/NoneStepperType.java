package com.holding.medis.components.stepper.internal.type;


import com.holding.medis.components.stepper.StepperLayout;

/**
 * Created by yccheok on 29/6/2017.
 */

public class NoneStepperType extends AbstractStepperType {
    public NoneStepperType(StepperLayout stepperLayout) {
        super(stepperLayout);
    }

    @Override
    public void onStepSelected(int newStepPosition, boolean userTriggeredChange) {
    }
}
