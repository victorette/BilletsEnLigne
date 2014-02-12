package ca.ulaval.ift6003.application.aspect;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AddLogger {

	private Logger logger = Logger.getLogger("trace");

	@Pointcut("execution(* ca.ulaval.ift6003.application.impl.*.*ajouter*(..) )")
	private void methodeAjouterElement() {
	}

	@Pointcut("execution(* *..PanierApplicationImpl.modifier*(..) )")
	private void methodeModifierElement() {
	}

	@After("methodeAjouterElement() || methodeModifierElement()")
	public void apresExecuterMethodeAjouter(JoinPoint joinPoint) {
		StringBuilder arguments = generateArgumentsString(joinPoint.getArgs());
		logger.logp(Level.INFO, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), arguments.toString());
	}

	@AfterThrowing(pointcut = "execution(* *..application.impl.*.*ajouter*(..))", throwing = "error")
	public void apresExecuterMethodeAjouterDoThrow(JoinPoint joinPoint, Throwable error) {
		logger.logp(Level.SEVERE, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), error.getMessage());
	}

	@Pointcut("execution(void *..application.impl.*.*effacer*(..))")
	private void apresExecuterMethodeEffacer() {
	}

	@Pointcut("execution(void *..application.impl.*.*supprimer*(..))")
	private void apresExecuterMethodeSupprimer() {
	}

	@After("apresExecuterMethodeEffacer() || apresExecuterMethodeSupprimer()")
	public void apresExecuterMethodeEffacer(JoinPoint joinPoint) {
		StringBuilder arguments = generateArgumentsString(joinPoint.getArgs());
		logger.logp(Level.INFO, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), arguments.toString());
	}

	@AfterThrowing(pointcut = "apresExecuterMethodeEffacer() || apresExecuterMethodeSupprimer()", throwing = "error")
	public void apresExecuterMethodeEffacerDoThrow(JoinPoint joinPoint, Throwable error) {
		logger.logp(Level.INFO, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), error.getMessage());
	}

	@Pointcut("execution(void *.log*Utilisateur*(..)) || execution(* *..UtilisateurManagementImpl.inscrire*(..))")
	private void apresExecuterLoginLogoutOuInscrire() {
	}

	@Pointcut("execution(* *..UtilisateurManagementImpl.modifier*(..))")
	private void apresModifierCompte() {
	}

	@After("apresExecuterLoginLogoutOuInscrire() || apresModifierCompte()")
	public void apresExecuterMethodeLogin(JoinPoint joinPoint) {
		StringBuilder arguments = generateArgumentsString(joinPoint.getArgs());
		logger.logp(Level.INFO, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), arguments.toString());
	}

	@AfterThrowing(pointcut = "apresExecuterLoginLogoutOuInscrire() || apresModifierCompte()", throwing = "error")
	public void apresExecuterMethodeLoginDoThrow(JoinPoint joinPoint, Throwable error) {
		logger.logp(Level.INFO, joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(), error.getMessage());
	}

	private StringBuilder generateArgumentsString(Object[] args) {
		StringBuilder builder = new StringBuilder();
		for (int length = args.length, i = 0; i < length; ++i) {
			builder.append(" " + args[i]);
		}
		return builder;
	}
}
