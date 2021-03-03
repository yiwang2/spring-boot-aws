package com.genesys.application.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.stream.Stream;
import com.genesys.application.model.ElevatorState;

@Converter(autoApply = true)
public class ElevatorStateConvertor implements AttributeConverter<ElevatorState, String>{

	@Override
	public String convertToDatabaseColumn(ElevatorState elevator) {
		if (elevator == null) {
            return null;
        }
        return elevator.getCode();
	}

	@Override
	public ElevatorState convertToEntityAttribute(String elevatorCode) {
		if (elevatorCode == null) {
            return null;
        }

        return Stream.of(ElevatorState.values())
          .filter(c -> c.getCode().equals(elevatorCode))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
	}

}
