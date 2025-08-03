package com.github.fabriciolfj.paymentcard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import java.util.Base64;

import static com.github.fabriciolfj.paymentcard.util.SecureConstants.TRANSFORMATION;

@Service
@RequiredArgsConstructor
public class ValidCardEncryptService {

    private final GetKeySecureService getKeySecureService;

    public boolean execute(final String text) {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
           cipher.init(Cipher.DECRYPT_MODE, getKeySecureService.execute());

           var textCode = Base64.getDecoder().decode(text);
           cipher.doFinal(textCode);

           return true;
        } catch (Exception _) {
            return false;
        }
    }
}
