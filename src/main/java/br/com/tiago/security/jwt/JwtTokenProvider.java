package br.com.tiago.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.com.tiago.exceptions.InvalidJwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtTokenProvider {

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "key";

	@Value("${security.jwt.token.expire-lenght:3600000}")
	private Long validityInMilliseconds = (long) 3600000; // 1h

	@Autowired
	private UserDetailsService userDetailsService;

	@PostConstruct
	public void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}

	public String creatToken(String userName, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(userName); /* certificação */
		claims.put("roles", roles);

		Date now = new Date(); /* Data Atual */
		Date validity = new Date(now.getTime() + validityInMilliseconds);/* Data de validade */

		return Jwts.builder().setClaims(claims)/* recebe minha claims ja declarada a cima */
				.setIssuedAt(now)/* A Data que o toque foi criado */
				.setExpiration(validity)/* data de expiração */
				.signWith(SignatureAlgorithm.HS256, secretKey)/* Alg resp por Encripitar */
				.compact();/* faz a compactação */

	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	private String getUserName(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean validateToken(String token) throws InvalidJwtAuthenticationException {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (Exception e) {
			throw new InvalidJwtAuthenticationException("Experid or Invalid Token.");
		}

	}

}
