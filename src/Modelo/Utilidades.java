package Modelo;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 *
 * @author Carlos Tarmeño
 */
public class Utilidades {
    /*
        MODIFICACIÓN
        @Carlos Tarmeño
        - Se llama al método 'hashToString', donde se establece como argumento 12 
        para el número de rondas de encriptación y se pasa la contraseña pasa como 
        un arreglo de caracteres de la contraseña.
        - El método retorna un string hasheado.
    */
    public static String encriptarPassword(String password) {
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return hashedPassword;
    }
}