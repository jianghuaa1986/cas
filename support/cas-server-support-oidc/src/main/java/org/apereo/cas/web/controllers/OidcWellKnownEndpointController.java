package org.apereo.cas.web.controllers;

import org.apereo.cas.OidcConstants;
import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.discovery.OidcServerDiscoverySettings;
import org.apereo.cas.authentication.principal.PrincipalFactory;
import org.apereo.cas.authentication.principal.ServiceFactory;
import org.apereo.cas.authentication.principal.WebApplicationService;
import org.apereo.cas.services.ServicesManager;
import org.apereo.cas.support.oauth.validator.OAuth20Validator;
import org.apereo.cas.support.oauth.web.BaseOAuthWrapperController;
import org.apereo.cas.ticket.accesstoken.AccessTokenFactory;
import org.apereo.cas.ticket.registry.TicketRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This is {@link OidcWellKnownEndpointController}.
 *
 * @author Misagh Moayyed
 * @since 5.0.0
 */
public class OidcWellKnownEndpointController extends BaseOAuthWrapperController {

    private final OidcServerDiscoverySettings discovery;

    public OidcWellKnownEndpointController(final ServicesManager servicesManager,
                                           final TicketRegistry ticketRegistry,
                                           final OAuth20Validator validator,
                                           final AccessTokenFactory accessTokenFactory,
                                           final PrincipalFactory principalFactory,
                                           final ServiceFactory<WebApplicationService> webApplicationServiceServiceFactory,
                                           final OidcServerDiscoverySettings discovery,
                                           final CasConfigurationProperties casProperties) {
        super(servicesManager, ticketRegistry, validator, accessTokenFactory,
                principalFactory, webApplicationServiceServiceFactory, casProperties);
        this.discovery = discovery;
    }

    /**
     * Gets well known discovery configuration.
     *
     * @return the well known discovery configuration
     */
    @GetMapping(value = '/' + OidcConstants.BASE_OIDC_URL + "/.well-known", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OidcServerDiscoverySettings> getWellKnownDiscoveryConfiguration() {
        return new ResponseEntity(this.discovery, HttpStatus.OK);
    }

    /**
     * Gets well known openid discovery configuration.
     *
     * @return the well known discovery configuration
     */
    @GetMapping(value = '/' + OidcConstants.BASE_OIDC_URL + "/.well-known/openid-configuration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OidcServerDiscoverySettings> getWellKnownOpenIdDiscoveryConfiguration() {
        return getWellKnownDiscoveryConfiguration();
    }
}
