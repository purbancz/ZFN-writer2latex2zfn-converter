package main.java.bibtex;

public class BibEntries{

	private String key;

	private String author;

	private String year;

	private String title;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "BibEntries [key=" + key + ", author=" + author + ", year=" + year + ", title=" + title + "]";
	}

//	@Override
//	public int compareTo(BibEntries entry) {
//		if (getTitle() == null || entry.getTitle() == null) {
//			return 0;
//		}
//		return getTitle().compareTo(entry.getTitle());
//	}

}
