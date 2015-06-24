package it.univr.is.entity;

/**
 * Entità per Libro con campo booleano per selezione
 * @author marco
 *
 */
public class LibroConSelezione extends Libro {

	private boolean select;

	public LibroConSelezione(){
		super();
		setSelect(false);
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}
	
}
