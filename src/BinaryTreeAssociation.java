
//BinaryTree extendido para guardar un value de Association y ser un Diccionario
public class BinaryTreeAssociation extends BinaryTree<Association<String, String>> {
	//Constructor sin valor
	public BinaryTreeAssociation() {
		this.setValue(null);
		this.left=null;
		this.right=null;
	}
	//Constructor con valor
	public BinaryTreeAssociation(Association<String,String> assoc) {
		this.setValue(assoc);
		this.left=null;
		this.right=null;
		this.parent=null;
	}
	
	//Traduce la palabra si existe en el diccionario sino devuelve la palabra con asteristico;
	public String englishToSpanish(String english) {
		String spanish= searchWord(this, english);
		if(spanish.equals("")) {
			spanish="*"+english+"*";
		}
		return spanish;
		
	}
	
	//Busca searchWord la palabra en ingles de forma in order. Devuelve el valor de la traduccion;
	private String searchWord(BinaryTree<Association<String,String>> tree, String english) { 
		String translation="";
		if(tree!=null) {
			if (tree.value().getEnglish().toLowerCase().equals(english.toLowerCase())){ 
				return (tree.value().getSpanish());
			}
		}
	
		if(tree.left!=null && translation.equals("")) {
			translation=searchWord(tree.left,english);
		}
		if(tree.right!=null && translation.equals("")) {
			translation=searchWord(tree.right,english);
		}
			
		return translation;
		
		}
	
	//Guarda las palabras en orden al arbol binario, insertando cada una de forma ordenada;
	public  void newTranslation(BinaryTree<Association<String,String>> tree) {
		if(this.value()==null && this.isRoot()) {
			this.setValue(tree.value());
		} else if(this.value().getEnglish().compareTo((tree.value().getEnglish()))<0) {
			if(this.right()==null && tree.value()!=null) {
				this.setRight(tree);
			}else{
				((BinaryTreeAssociation) this.right).newTranslation(tree);
			}
		}else {
	
			if(this.left()==null && tree!=null) {
				this.setLeft(tree);
			}else {
				((BinaryTreeAssociation) this.left).newTranslation(tree);
			}
		}
			
	}
	
	//Recorre el arbol de manera inOrder
	public void inOrderAssociation() {
		inOrderAssociation(this);
	  }
	//Recorre el arbol de manera inOrder, hijoIzquierdo, padre, hijoDerecho
	 private void inOrderAssociation(BinaryTreeAssociation tree) { 
		if (tree == null ) 
			{ return; } 
		
		inOrderAssociation((BinaryTreeAssociation)tree.left());
		System.out.println("("+tree.value().getEnglish()+","+tree.value().getSpanish()+")");
		inOrderAssociation((BinaryTreeAssociation)tree.right());
		
	}
		
}
