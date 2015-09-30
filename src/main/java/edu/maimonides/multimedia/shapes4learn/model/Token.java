package edu.maimonides.multimedia.shapes4learn.model;

/**
 * This empty class represents a Token generated by the lexical analyzer. It
 * will be implemented by the students according to the language and interpreter
 * requirements.
 * 
 * @author Matias Giorgio
 * 
 */
public class Token {

	public String lexema;
	public String clase;
	
	public String getLexema() {
		return lexema;
	}
	public void setLexema(String lexema) {
		this.lexema = lexema;
	}
	public String getClase() {
		return clase;
	}
	public void setClase(String clase) {
		this.clase = clase;
	}
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lexema: ");
        sb.append(lexema);
        sb.append(" ||| Clase: ");
        sb.append(clase);
        sb.append("\n");
        return sb.toString();
    }    

}
