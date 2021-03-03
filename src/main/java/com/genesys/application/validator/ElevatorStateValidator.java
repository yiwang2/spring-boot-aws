package com.genesys.application.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.genesys.application.model.ElevatorState;

/**
 * Customised validator for enum ElevatorState
 * Example:
 * @ElevatorStateSubset(anyOf = {ElevatorState.UP, ElevatorState.DOWN, ElevatorState.STOPPED, ElevatorState.OUT_OF_SERVICE})
 * private ElevatorState state;
 *
 */
public class ElevatorStateValidator implements ConstraintValidator<ElevatorStateSubset, ElevatorState>{

	private ElevatorState[] subset;
	
	@Override
    public void initialize(ElevatorStateSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(ElevatorState value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }

}
