package co.edu.uco.taskpeak.crosscutting.helpers;

import static co.edu.uco.taskpeak.crosscutting.helpers.ObjectHelper.getObjectHelper;

public final class TextHelper {

	public static final String EMPTY = "";
	public static final String UNDERLINE = "_";

	private TextHelper() {
		super();
	}

	public static final boolean isNull(final String string) {
		return getObjectHelper().isNull(string);
	}

	public static final boolean isNullOrEmpty(final String string) {
		return isNull(string) || EMPTY.equals(applyTrim(string));
	}

	public static final boolean isEmpty(final String string) {
		return string == null || string.trim().isEmpty();
	}

	public static final String applyTrim(final String string) {
		return getDefault(string).trim();
	}

	public static final String getDefault(final String string, final String defaultValue) {
		return getObjectHelper().getDefault(string, defaultValue);
	}

	public static final String getDefault(final String string) {
		return getDefault(string, EMPTY);
	}

	public static final String concatenate(final String... strings) {
		final var sb = new StringBuilder(EMPTY);

		if (!ObjectHelper.getObjectHelper().isNull(strings)) {
			for (final var string : strings) {
				sb.append(applyTrim(string));
			}
		}

		return sb.toString();
	}

	public static final boolean isValidEmail(final String email) {
		return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
	}

	public static final boolean isValidPasswordLength(final String password) {
		return password != null && password.length() >= 8;
	}
}