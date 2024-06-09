package co.edu.uco.tutorspace.crosscutting.helpers;

import java.util.regex.Pattern;

public final class TextHelper {

    public static final String EMPTY = "";
    public static final String UNDERLINE = "_";
    private static final String LISTA_SOLO_LETRAS = "^[A-Za-záéíóúÁÉÍÓÚ\\s]+$";
    private static final String LISTA_SOLO_LETRAS_DIGITOS_ESPACIOS = "^[0-9A-Za-záéíóúÁÉÍÓÚ\\s]+$";
    private static final String PATTERN_CORREO_ELECTRONICO = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String PATTERN_NUMEROS = "^[0-9]+$";

    private TextHelper() {
        super();
    }

    public static final boolean isNull(final String string) {
        return ObjectHelper.getObjectHelper().isNull(string);
    }

    public static final boolean isNullOrEmpty(final String string) {
        return isNull(string) || EMPTY.equals(applyTrim(string));
    }

    public static final String getDefaultValue(final String string, final String defaultValue) {
        return ObjectHelper.getObjectHelper().getDefaultValue(string, defaultValue);
    }

    public static final String getDefaultValue(final String string) {
        return getDefaultValue(string, EMPTY);
    }

    public static final String applyTrim(final String string) {
        return getDefaultValue(string).trim();
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

    public static String reemplazarParametro(String mensaje, String... parametros) {
        String mensajeReemplazado = mensaje;
        for (int i = 0; i < parametros.length; i++) {
            String marcador = "$[" + (i + 1) + "}";
            mensajeReemplazado = mensajeReemplazado.replace(marcador, parametros[i]);
        }
        return mensajeReemplazado;
    }

    public static boolean longitudMinimaPermitida(final String valor, final int longitud) {
        return applyTrim(valor).length() >= longitud;
    }

    public static boolean longitudMaximaPermitida(final String valor, final int longitud) {
        return applyTrim(valor).length() <= longitud;
    }

    public static final boolean soloLetras(final String valor) {
        return getDefaultValue(valor).matches(LISTA_SOLO_LETRAS);
    }

    public static final boolean soloLetrasDigitosEspacios(final String valor) {
        return getDefaultValue(valor).matches(LISTA_SOLO_LETRAS_DIGITOS_ESPACIOS);
    }

    public static final boolean contieneFormatoCorreo(final String valor) {
        return getDefaultValue(valor).matches(PATTERN_CORREO_ELECTRONICO);
    }

    public static final boolean contieneSoloDigitos(final String valor) {
        return getDefaultValue(valor).matches(PATTERN_NUMEROS);
    }

    public static boolean validarClave(String contraseña) {
        boolean contieneMayuscula = !contraseña.equals(contraseña.toLowerCase());
        boolean contieneMinuscula = !contraseña.equals(contraseña.toUpperCase());
        boolean contieneNumero = contraseña.matches(".*\\d.*");
        Pattern patronCaracterEspecial = Pattern.compile("[!@#$%^&*()\\[\\]{};:,.<>?]");
        boolean contieneCaracterEspecial = patronCaracterEspecial.matcher(contraseña).find();
        return contieneMayuscula && contieneMinuscula && contieneNumero && contieneCaracterEspecial;
    }
}

