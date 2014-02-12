package ca.ulaval.ift6003.tests.unit.interfaces.filters;

import static org.mockito.Mockito.*;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.interfaces.filters.ModificationCompteFilter;

public class ModificationCompteFilterTest {

	private ModificationCompteFilter modificationCompteFilter;
	private ServletRequest mockServletRequest;
	private HttpSession mockSession;
	private ServletResponse mockServletResponse;
	private FilterChain mockFilterChain;
	private UtilisateurManagement mockUtilisateurManagement;

	private String permissions;
	private static String MANAGEMENT = "utilisateurManagement";
	private static String LOGIN_URL = "null/unsecured-pages/login.xhtml";
	private static String ACCUEIL_URL = "null/secured-pages/accueil.xhtml";

	@Before
	public void setUp() throws Exception {
		modificationCompteFilter = new ModificationCompteFilter();
		mockServletRequest = mock(ServletRequest.class, withSettings().extraInterfaces(HttpServletRequest.class));
		mockSession = mock(HttpSession.class);
		mockServletResponse = mock(ServletResponse.class, withSettings().extraInterfaces(HttpServletResponse.class));
		mockFilterChain = mock(FilterChain.class);
		mockUtilisateurManagement = mock(UtilisateurManagement.class);
	}

	@Test
	public void quandLoginUtilisateurNullAlorsRedirectToLogin() throws IOException, ServletException {
		when(modificationCompteFilter.GetSession(mockServletRequest)).thenReturn(mockSession);
		when(mockSession.getAttribute(MANAGEMENT)).thenReturn(mockUtilisateurManagement);

		modificationCompteFilter.doFilter(mockServletRequest, mockServletResponse, mockFilterChain);

		verify((HttpServletResponse) mockServletResponse).sendRedirect(LOGIN_URL);
	}

	@Test
	public void quandLoginUtilisateurManagerAlorsRedirectToAccueil() throws IOException, ServletException {
		when(modificationCompteFilter.GetSession(mockServletRequest)).thenReturn(mockSession);
		when(mockSession.getAttribute(MANAGEMENT)).thenReturn(mockUtilisateurManagement);
		when(mockUtilisateurManagement.utilisateurEstConnecte()).thenReturn(true);
		when(mockUtilisateurManagement.utilisateurActifALeDroitDe(permissions)).thenReturn(true);

		modificationCompteFilter.doFilter(mockServletRequest, mockServletResponse, mockFilterChain);

		verify((HttpServletResponse) mockServletResponse).sendRedirect(ACCUEIL_URL);
	}

}
