package top.after.rule.engine.compile;

public class CompileException extends RuntimeException {
	private static final long serialVersionUID = 6684054584468120800L;

	public CompileException() {
	}

	public CompileException(String arg0) {
		super(arg0);
	}

	public CompileException(Throwable arg0) {
		super(arg0);
	}

	public CompileException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
