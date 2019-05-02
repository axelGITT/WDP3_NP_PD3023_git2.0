package zad3;

public class Pakiet {

	private int nr;
	private long size;

	public Pakiet(int nr, long size) {
		this.nr = nr;
		this.size = size;
	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String toString() {
		return nr + " " + size;
	}

}
