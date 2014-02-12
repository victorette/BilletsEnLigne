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
import ca.ulaval.ift6003.interfaces.filters.LoginFilter;

public class LoginFilterTest {

	private ServletRequest mockServletRequest;
	private HttpSession mockSession;
	private ServletResponse mockServletResponse;
	private FilterChain mockFilterChain;
	private LoginFilter loginFilter;
	private UtilisateurManagement mockUtilisateurManagement;
	private static String MANAGEMENT = "utilisateurManagement";
	private static String LOGIN_URL = "null/unsecured-pages/login.xhtml";

	@Before
	public void setUp() throws Exception {
		loginFilter = new LoginFilter();
		mockServletRequest = mock(ServletRequest.class, withSettings().extraInterfaces(HttpServletRequest.class));
		mockSession = mock(HttpSession.class);
		mockServletResponse = mock(ServletResponse.class, withSettings().extraInterfaces(HttpServletResponse.class));
		mockFilterChain = mock(FilterChain.class);
		mockUtilisateurManagement = mock(UtilisateurManagement.class);
	}

	@Test
	public void quandLoginUtilisateurNonConnecteAlorsRedirect() throws IOException, ServletException {
		when(loginFilter.GetSession(mockServletRequest)).thenReturn(mockSession);
		when(mockSession.getAttribute(MANAGEMENT)).thenReturn(mockUtilisateurManagement);

		loginFilter.doFilter(mockServletRequest, mockServletResponse, mockFilterChain);

		verify((HttpServletResponse) mockServletResponse).sendRedirect(LOGIN_URL);
	}

	@Test
	public void quandLoginUtilisateurConnecteAlorsPass() throws IOException, ServletException {
		when(loginFilter.GetSession(mockServletRequest)).thenReturn(mockSession);
		when(mockSession.getAttribute(MANAGEMENT)).thenReturn(mockUtilisateurManagement);
		when(mockUtilisateurManagement.utilisateurEstConnecte()).thenReturn(true);

		loginFilter.doFilter(mockServletRequest, mockServletResponse, mockFilterChain);

		verify(mockFilterChain).doFilter(mockServletRequest, mockServletResponse);
	}

}
