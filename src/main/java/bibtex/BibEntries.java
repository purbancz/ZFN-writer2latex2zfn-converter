package main.java.bibtex;

public class BibEntries {

	private String key;

	private String author;

	private String year;

	public BibEntries(String key, String author, String year) {
		this.key = key;
		this.author = author;
		this.year = year;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "BibEntries [key=" + key + ", author=" + author + ", year=" + year + "]";
	}

}
