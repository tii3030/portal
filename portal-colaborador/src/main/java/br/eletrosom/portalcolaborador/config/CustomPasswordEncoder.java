package br.eletrosom.portalcolaborador.config;

import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/*
 * 	Altera o algoritmo de codificação da senha para MD5
*/
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return DigestUtils.md5DigestAsHex(rawPassword.toString().getBytes());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String rawEncoded = encode(rawPassword);
        return Objects.equals(rawEncoded, encodedPassword);
	}

}
