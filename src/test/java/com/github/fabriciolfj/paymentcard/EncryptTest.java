package com.github.fabriciolfj.paymentcard;

import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class EncryptTest {

    private static final String KEY_FIXTURE = "test";
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    private static final SecretKeySpec SECRET_KEY = criarChave();

    private static SecretKeySpec criarChave() {
        byte[] key = new byte[16];
        byte[] chaveBytes = KEY_FIXTURE.getBytes();
        System.arraycopy(chaveBytes, 0, key, 0, Math.min(chaveBytes.length, 16));
        return new SecretKeySpec(key, ALGORITHM);
    }

    private static String criptografar(String texto) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);
            byte[] textoCriptografado = cipher.doFinal(texto.getBytes());
            return Base64.getEncoder().encodeToString(textoCriptografado);
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean isValido(String texto) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);

            byte[] dadosDecodificados = Base64.getDecoder().decode(texto);
            cipher.doFinal(dadosDecodificados);

            return true;
        } catch (Exception _) {
            return false;
        }
    }

    private static String descriptografar(String textoCriptografado) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);

            byte[] dadosDecodificados = Base64.getDecoder().decode(textoCriptografado);
            byte[] textoOriginal = cipher.doFinal(dadosDecodificados);

            return new String(textoOriginal);
        } catch (Exception e) {
            return null;
        }
    }

    @Test
    void testEncryptText() {
        var texto = "412722819233789";
        var textoEncryptado = criptografar(texto);
        System.out.println(textoEncryptado);

        var textEncryptadoValido = isValido(textoEncryptado);
        System.out.println(textEncryptadoValido);
    }


}
