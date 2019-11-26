package br.com.wellmartins.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager; // cara que consegue pegar minha senha
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient("angular") //user do cliente, do serviço 
		.secret("@ngul@r0") //senha do cliente, do serviço 
		.scopes("read", "write") // se nao colocar nada no scopo da erro ao subir a app
		.authorizedGrantTypes("password", "refresh_token") // infomando que o tipo é password flow do oauth2
		.accessTokenValiditySeconds(20) // tempo de vida do token
		.refreshTokenValiditySeconds(3600 * 24); // tempo de expiração do refresh token, (3600 é uma hora)
		
	}
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore())/// informando onde esta armazendo o token
		.accessTokenConverter(accessTokenConverter())
		.reuseRefreshTokens(false)
		.authenticationManager(authenticationManager); // e informando o authenticationManager que é o cara que vai conseguir validar
									// usuario e senhas e tals
	}
	
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
		accessTokenConverter.setSigningKey("algaworks");
		return accessTokenConverter;
	}
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
}
