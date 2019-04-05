

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;

public class MyTests {

    @Test
    public void TestBinaryTreeDictionary() {
    	BinaryTreeAssociation Dictionary = new BinaryTreeAssociation();
    	//Se lee el archivo txt con las palabras traducidas que tendra el diccionario
    			try {
    				Stream<String> lines = Files.lines(Paths.get("diccionarioprueba.txt"),StandardCharsets.UTF_8);
    				lines.forEach(i->{
    					String english = i.substring(i.indexOf("(")+1, i.indexOf(","));
    					String spanish = i.substring(i.indexOf(",")+2, i.indexOf(")"));
    					Association<String, String> association = new Association<String,String>(english,spanish);
    					BinaryTreeAssociation SubTree = new BinaryTreeAssociation(association);
    					Dictionary.newTranslation(SubTree);
    				});
    			} catch (IOException e) {
    				// TODO: handle exception
    				e.printStackTrace();
    			}
    		//Ya se ha agregado todo al arbol binario que contiene nuestro dicccionario.
    		//Se comienzan a realizar pruebas con traducciones
    		//Aqui guarda la palabra traducida
    		//Esta referencia es un hoja
    		Boolean isLeaf=Dictionary.right().isLeaf();
    		assertEquals(false, isLeaf,"Este arbol  no es una hoja, pues tiene 2 hijos." );
    			
    }

	
}