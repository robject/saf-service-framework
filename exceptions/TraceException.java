package sepher.saf.exceptions;

/**
 * Een Trace Exception geeft de mogelijkheid om de 
 * oorsprong van de exceptie te bewaren.
 *
 * Creation date: (5/27/02 1:02:18 PM)
 * @author: LPR
 */
public abstract class TraceException extends Exception {
	private Exception causeException = null;
/**
 * Constructor voor TraceException waarbij er geen melding of cause
 * exception gegeven is.
 */
public TraceException() {
	super();
}
/**
 * Constructor voor TraceException.
 * @param e De exceptie die is opgetreden.
 */
public TraceException(Exception e) {
	super();
	this.setCauseException(e);
}
/**
 * Constructor voor TraceException.
 * @param s De boodschap van de exceptie.
 */
public TraceException(String s) {
	super(s);
}
/**
 * Constructor voor TraceException.
 * @param s De boodschap van de exceptie.
 * @param e De exceptie die is opgetreden.
 */
public TraceException(String s, Exception e) {
	super(s);
	this.setCauseException(e);
}
/**
 * Geef de bron van deze exceptie.
 * Creation date: (5/27/02 1:02:41 PM)
 * @return De exceptie.
 */
public Exception getCauseException() {
	return this.causeException;
}
/**
 * Geef de tekstuele omschrijving van deze exceptie.
 * Creation date: (5/27/02 1:20:39 PM)
 * @return De meldingstekst.
 */
public String getCauseMessage() {
  if (this.getCauseException() != null) {
    return this.getCauseException().getMessage();
  }
	return null;
}
/**
 * Bewaar de bron van deze exceptie.
 * Creation date: (5/27/02 1:02:41 PM)
 * @param newCauseException Een exceptie.
 */
private void setCauseException(Exception newCauseException) {
	this.causeException = newCauseException;
}
}
