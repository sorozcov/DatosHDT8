
public class Association<K, V> {
	
	private K english;
	private V spanish;
	
	/**
	 * @param english la palabra en ingles
	 * @param spanish la palabra en espaniol
	 */
	public Association(K english, V spanish) {
		// TODO Auto-generated constructor stub
		this.english = english;
		this.spanish = spanish; 
	}

	/**
	 * @return the english
	 */
	public K getEnglish() {
		return english;
	}

	/**
	 * @param english the english to set
	 */
	public void setEnglish(K english) {
		this.english = english;
	}

	/**
	 * @return the spanish
	 */
	public V getSpanish() {
		return spanish;
	}

	/**
	 * @param spanish the spanish to set
	 */
	public void setSpanish(V spanish) {
		this.spanish = spanish;
	}

}
