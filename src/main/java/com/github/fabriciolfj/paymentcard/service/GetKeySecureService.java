package com.github.fabriciolfj.paymentcard.service;

import com.github.fabriciolfj.paymentcard.configuration.KeyConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;

import static com.github.fabriciolfj.paymentcard.util.SecureConstants.ALGORITHM;

@Service
@RequiredArgsConstructor
public class GetKeySecureService {

    private final KeyConfiguration keyConfiguration;

    public SecretKeySpec execute() {
        final byte[] key = new byte[16];
        final byte[] chaveBytes = keyConfiguration.getValue().getBytes();
        System.arraycopy(chaveBytes, 0, key, 0, Math.min(chaveBytes.length, 16));

        return new SecretKeySpec(key, ALGORITHM);
    }
}
