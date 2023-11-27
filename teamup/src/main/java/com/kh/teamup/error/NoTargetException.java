package com.kh.teamup.error;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoTargetException extends RuntimeException{

	public NoTargetException(String message) {
		super(message);
	}
}
