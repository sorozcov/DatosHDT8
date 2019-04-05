import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/*Silvio Orozco 18282
 * Jose Castañeda 18161*/
public class Main {

	public static void main(String[] args){

		//Se crea el scanner 
		Scanner s = new Scanner(System.in);
		//Variable que valida lo ingresado por el usuario
		Boolean validado = false;
		//Variable 	que almacena la opcion del menu seleccionada
		int menu = 0;
		//Se crea el binary Tree
		BinaryTreeAssociation DictionaryRoot = new BinaryTreeAssociation();
		
		//Se lee el archivo txt con las palabras traducidas que tendra el diccionario
		try {
			Stream<String> lines = Files.lines(Paths.get("diccionario.txt"),StandardCharsets.UTF_8);
			lines.forEach(i->{
				String english = i.substring(i.indexOf("(")+1, i.indexOf(","));
				String spanish = i.substring(i.indexOf(",")+2, i.indexOf(")"));
				Association<String, String> association = new Association<String,String>(english,spanish);
				BinaryTreeAssociation SubTree = new BinaryTreeAssociation(association);
				DictionaryRoot.newTranslation(SubTree);
			});
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		//Se muestra el menu	
		do {
			System.out.println("Ingresa el número de una opcion del menu:\n1.Mostrar la colección ordenada por ingles\n2.Traducir archivo texto.txt\n3.Salir");
			do{
				//Se setea validado falso
				validado = false;
				String menuStr = s.nextLine();
				try {
					menu = Integer.parseInt(menuStr);
					if((menu>=1)&&(menu<=3)) {
						validado = true;
					}else {
						System.out.println("Ingrese una opción correcta");
						validado = false;
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Ingrese una opción correcta");
					validado = false;
				}
			}while(validado==false);
					
			//Funcion 1 Se realiza el in Order Association
			if(menu==1) {
				DictionaryRoot.inOrderAssociation();
			}
			
			//Funcion 2
			//Se pide un archivo para traducir y luego lo muestra traducido
			if(menu==2) {
				boolean errorOnFile=false;
				String fileDirectory;
				
				do {	
					System.out.println("Ingresa la direccion del archivo para traducir el texto: ");
					fileDirectory = s.nextLine();
					try {
						//Se lee el archivo
						 Scanner scann = new Scanner(new File(fileDirectory));
						 String line = "";
							String traduction="\t";
							String textForTraduction="\t";
							while (scann.hasNextLine()) {
								line = scann.nextLine()+ " ";
								//Se hace un arreglo de strings
								String[] text = line.split(" ");
								//Se recorre la linea del archivo txt
								for (int i = 0; i < text.length; i++) {
									String word = text[i];
									String colons ="";
									if(word.contains(".")){
										if(word.indexOf(".")==(word.length()-1))
										word=word.substring(0, word.indexOf("."));
										colons=".";
									}
									if(word.contains(",")){
										if(word.indexOf(",")==(word.length()-1))
										word=word.substring(0, word.indexOf(","));
										colons=",";
									}
									textForTraduction = " " + textForTraduction + word + colons + " ";
									traduction =  " "+ traduction + DictionaryRoot.englishToSpanish(word)+colons + " ";
								}
								
							}
							//Muestra el texto inicial y el texto final.
							System.out.println("Texto a Traducir");
							System.out.println(textForTraduction);
							System.out.println("Texto Traducido");
							System.out.println(traduction);
						errorOnFile=false;
					} catch (Exception e) {
						//Verifica que la direccion sea valida
						System.out.println("Error en datos ingresado");
						System.out.println("Verifica la dirección del archivo ingresado sea correcta");
						errorOnFile=true;
					
					}
				}while(errorOnFile);
				
			}
			//Sale del menu
		} while (menu!=3);
	}
}
