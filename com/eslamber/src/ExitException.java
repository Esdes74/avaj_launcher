package com.eslamber.src;

// Exception vérifiée (hérite de Exception)
public class ExitException extends Exception {
	private	int code;

    public	ExitException(int p_code) {
		code = p_code;
    }

	public	int getCode() { return code; }
}