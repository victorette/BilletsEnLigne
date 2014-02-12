package ca.ulaval.ift6003.interfaces.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ca.ulaval.ift6003.application.UtilisateurManagement;
import ca.ulaval.ift6003.domaine.modele.ConstantesEtEnums.Permissions;

public class ModificationCompteFilter implements Filter {

	/**
	 * Vérifie si l'utilisateurActif est connecté. Si non, il redirige vers la page login.xhtml
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (!response.isCommitted()) {
			HttpSession session = GetSession(request);
			UtilisateurManagement utilisateurManagement = (UtilisateurManagement) session.getAttribute("utilisateurManagement");

			if (utilisateurManagement == null || !utilisateurManagement.utilisateurEstConnecte()) {
				String contextPath = ((HttpServletRequest) request).getContextPath();
				((HttpServletResponse) response).sendRedirect(contextPath + "/unsecured-pages/login.xhtml");
			} else if (!utilisateurPeutModifierInfosCompte(utilisateurManagement)) {
				String contextPath = ((HttpServletRequest) request).getContextPath();
				((HttpServletResponse) response).sendRedirect(contextPath + "/secured-pages/accueil.xhtml");
			}
			chain.doFilter(request, response);
		}
	}

	public HttpSession GetSession(ServletRequest request) {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		return hrequest.getSession();
	}

	private boolean utilisateurPeutModifierInfosCompte(UtilisateurManagement utilisateurManagement) {
		return utilisateurManagement.utilisateurActifALeDroitDe(Permissions.MODIFICATION_COMPTE);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// Rien à faire ici!
	}

	@Override
	public void destroy() {
		// Rien à faire ici!
	}

}
