package com.jlc.api.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class HBException extends RuntimeException {
	private static final long serialVersionUID = 479788007437470106L;
	private Throwable cause;

	
    public HBException(String msg) {
		super(msg);
	}

    
    public HBException(Throwable ex) {
		this.cause = ex;
	}

    
	public HBException(String msg, Throwable ex) {
		super(msg);
		this.cause = ex;
		
	}

    
	public Throwable getCause() {
		return (this.cause == this ? null : this.cause);
	}

    
	public String getMessage() {
		if (this.cause == null || this.cause == this) {
			return super.getMessage();
		}
		else {
			return super.getMessage() + "; Schedule exception is " + this.cause.getClass().getName() +
					": " + this.cause.getMessage();
		}
	}

    
	public void printStackTrace(PrintStream ps) {
		if (this.cause == null || this.cause == this) {
			super.printStackTrace(ps);
		}
		else {
			ps.println(this);
			this.cause.printStackTrace(ps);
		}
	}

   
	public void printStackTrace(PrintWriter pw) {
		if (this.cause == null || this.cause == this) {
			super.printStackTrace(pw);
		}
		else {
			pw.println(this);
			this.cause.printStackTrace(pw);
		}
	}
}
