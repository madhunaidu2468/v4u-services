package com.mobifever.we4u.exception;

public final class We4UException extends Exception{

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private int severityLevel;

	public String getErrorCode() {
		return this.errorCode;
	}

	public String setErrorCode(final String errorCode) {
		this.errorCode = errorCode;
		return this.errorCode;
	}

	public int getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(final int severityLevel) {
		this.severityLevel = severityLevel;
	}

	public We4UException() {
		super();
	}

	public We4UException(String msg) {
		super(msg);
	}

	public We4UException(final String message,
			final String errorCode, final int serverityLevel, final Throwable cause) {
		super(message, cause);
		setErrorCode(errorCode);
		setSeverityLevel(severityLevel);
	}

	public We4UException(String message, Throwable cause) {
		super(message, cause);

	}

	public We4UException(Throwable cause) {
		super(cause);
	}

}