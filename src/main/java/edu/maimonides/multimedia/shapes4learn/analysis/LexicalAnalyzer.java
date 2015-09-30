package edu.maimonides.multimedia.shapes4learn.analysis;

import java.util.LinkedList;
import java.util.List;
import edu.maimonides.multimedia.shapes4learn.model.Token;

/**
 * This class is responsible for the first part of the interpreter: lexical
 * analysis. It will be implemented by the students to perform the proper
 * operations.
 * 
 * @author Matias Giorgio
 * 
 */

public class LexicalAnalyzer {
	

	
	
	private Token t;

	public List<Token> analyze(String code) throws LexicalException {
		
		String[][] reservada = new String[20][20];
		//String[] aux;
		boolean validoNum=false;
		//boolean validoId=true;
		boolean ExpArit=true;
		boolean reservada1=false;
		boolean id=false;
		
		//char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	      //      'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F' };
		
		// Completo la matriz token (lexemas - clase)
		
		reservada[0][0] = "create";       reservada[0][1] = "crear";
		
		reservada[1][0] = "rectangle";    reservada[1][1] = "ShapeRectangulo";
		
		reservada[2][0] = "circle"; 	  reservada[2][1] = "ShapeCirculo";
		
		reservada[3][0] = ";"; 			  reservada[3][1] = "fin de sentencia";
		
		reservada[4][0] = "setbase";      reservada[4][1] = "setear base";
		
		reservada[5][0] = "sethight";     reservada[5][1] = "setear altura";
		
		reservada[6][0] = "setcolor";     reservada[6][1] = "setear color";
		
		reservada[7][0] = "setradius";    reservada[7][1] = "setear radio";
		
		reservada[8][0] = "setposition";  reservada[8][1] = "setear posicion";
		
		reservada[9][0] = "shape";       reservada[9][1] = "clase shape";
		
		reservada[10][0] = ",";           reservada[10][1] = "separador de expresiones";
		
		reservada[11][0] = "in";           reservada[11][1] = "'in'";
		
		Token temp = new Token();
		List<Token> tokens = new LinkedList<>();
		
		// Separo los lexemas
		String delimitadores= "[\\ \\	\\  \\   \\    \\		]";
		String lexemas[] = code.split(delimitadores);
		
		//Recorro los lexemas y encuentro las palabras reservadas del lenguaje
		for(int i=0; i< lexemas.length; i++) {
		
			reservada1=false;
			validoNum=false;
			//boolean validoId=true;
			ExpArit=false;
			reservada1=false;
			id=false;
			
			System.out.println("\nPosicion: " + i + " <><> Lexema que ingresa: " + lexemas[i]); 
			
			System.out.println("Token: ");
			
			for (int j=0;j<=11;j++)
			{
				
				if (lexemas[i].equals(reservada[j][0]))
				{
					// Cuando lo encuentro, como se pasa a la linkedlisto token????
					
					reservada1=true;
					
					temp.lexema = reservada[j][0];
					temp.clase = reservada [j][1];
				}
			}					
					// si no es palabra reservada
					
					int x=0;
					
					// veo si es numero
				    for (;x<lexemas[i].length() && Character.isDigit(lexemas[i].charAt(x)) ;x++)
				     	{ 
				    		validoNum=true;
				        }
				    
				    if (validoNum) {
				   
				    	temp.lexema = lexemas[i];
			        	temp.clase = "Numero";
						
					} else {
						if (reservada1==false)
						{
						temp.lexema = lexemas[i];
			        	temp.clase = "Palabra desconocida";
						}
					}
				    
				    
				    //Expresion aritmetica

				    int f=0;
				    char ant='a';
				    
				    for (;(f<lexemas[i].length()) && (reservada1==false);f++)
			        { 
				    	
			        	if (Character.isDigit(lexemas[i].charAt(f)))
			        			{
			    					ant=lexemas[i].charAt(f);
			        			} else
			        			
			        			{
			        				if ((lexemas[i].charAt(f) == '+' || lexemas[i].charAt(f) == '-' ||
			        					 lexemas[i].charAt(f) == '*' || lexemas[i].charAt(f) == '/')
			        					&& Character.isDigit(ant))
			        				{
			        					ant=lexemas[i].charAt(f);
			        					ExpArit=true;
			        				}else
			        				{
			        					ExpArit=false;
			        				}
			        			}
			        }
				    
			        if (ExpArit)
			        {
			        	temp.lexema = lexemas[i];
			        	temp.clase = "Expresion Aritmetica";
				    //tokens.add(temp);
			        }else
			        { if (reservada1 == false && validoNum==false)
			        	{
			        	temp.lexema = lexemas[i];
			        	temp.clase = "Palabra desconocida";
			        	}
			        }
			        
				    //ID bien
			        
			        //System.out.println("Entra en ID el lexema " + lexemas[i]); 
			        
			       /* int t=0;
			        
				    for (;t<lexemas[t].length() && Character.isLetter(lexemas[t].charAt(t))  && reservada1==false ;t++)
			        {
					    temp.lexema = lexemas[t];
					    temp.clase = "ID";
					   // tokens.add(temp);	
			        }*/
			        
			        if (lexemas[i].matches("([a-z]|[A-Z]|\\s)+") && reservada1==false) {
			        	temp.lexema = lexemas[i];
					    temp.clase = "ID";
					    id=true;
					}  
				    	        
				    //color_def

				    //System.out.println("Entra en Color_def el lexema " + lexemas[i]); 
				    

				    if ( lexemas[i].charAt(0)=='#')
				    {
				    	if (lexemas[i].substring(1).matches("([a-f]|[A-F]|[0-9]|\\s)+")) {
				    		 temp.lexema = lexemas[i];
							 temp.clase = "color_def";
						}else {
							temp.lexema = lexemas[i];
							temp.clase = "Palabra desconocida";
						}
				   	}
				    				    
				    //Hexadecimal
				    //System.out.println("Entra en hexadecimal el lexema " + lexemas[i]); 
				    
				    if (lexemas[i].matches("([a-f]|[A-F]|[0-9]|\\s)+") && validoNum==false) {
				    	 temp.lexema = lexemas[i];
						 temp.clase = "color_def";
					}

				    System.out.println("Token lexema: " + i + " " + temp.lexema + " Token clase: " + temp.clase);
				    
				    t = temp;
				    
				    tokens.add(t);
				    
			    	
			}  
	
		
		return tokens;
	}
}
