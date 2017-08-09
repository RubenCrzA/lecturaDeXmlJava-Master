/*
 * @Author: ITIC. Ruben Cruz Aguilar
 * Fecha: 09-Agosto-2017
 * Descripcion: Este programa lee los nodos y atributos de un xml version 1.0 devolviendo el nivel de los nodos y regresando el atibuto de cada nodo
 * 
 */

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.*;


public class leerXml {
  
  public static int nivel=0;
  public static String space="";
	
  public static void main(String[] args) {
    try {
      
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      // creamos el loader del documento:
      DocumentBuilder loader = factory.newDocumentBuilder();
 
      // cargamos el arbol del DOM
      Document document = loader.parse("RutaDeTuXml");
      // obtenemos el primer elemento de nuestro xml(nodo padre)
      Element tree = document.getDocumentElement();
      //llamamos a la funcion leer nodos
      leerNodos(tree);
      
 
    } catch (IOException ex) {
      handleError(ex);
    } catch (SAXException ex) {
      handleError(ex);
    } catch (ParserConfigurationException ex) {
      handleError(ex);
    } catch (FactoryConfigurationError ex) {
      handleError(ex);
    }
  }
  
  //funcion para controlar las excepciones
  private static final void handleError(Throwable ex) {
	  System.out.println("se ha producido el siguiente error-->"+ ex.getMessage());
	  }
    
  // Funcion de leeer nodos
  public static void leerNodos(Node tree){
	
  //La variable space  solo sierve para dar formato al xml en cuanto se valla leyendo e imprimiendo en consola
	//Al igual que la anterior tambien nivel sirve para determinar en que nivel se encontro el nodo
  
	  space="";
	  if(tree.getNodeType()==1){
		  for(int a=0;a<nivel;a++){
			   space = space+"\t";
		  }//fin del for que determina el espaciado (se puede omitir)
		  System.out.println(space+tree.getNodeName());
		  NamedNodeMap map = tree.getAttributes();
		  for(int j=0;j<map.getLength();j++){
			  System.out.println(space+ "	-->"+map.item(j).getNodeName()+" --> "+map.item(j).getNodeValue());
		  }//findel for
	  }//fin del f
	  if(tree.hasChildNodes()){
		    nivel++;
          NodeList node =  tree.getChildNodes();
          for(int i=0 ; i<node.getLength();i++){
                leerNodos(node.item(i));
            }//fin del for
    	  nivel--;
    	  }//fin del if haschildNodes
      }//fin de leerNodos
  }//fin del documento
  
