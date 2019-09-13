package main.java.bibtex;

public class BibReferences {

	private String prefix;
	private String key;
	private String suffix;

	public BibReferences(String prefix, String key, String suffix) {
		this.prefix = prefix;
		this.key = key;
		this.suffix = suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public String toString() {
		return "[" + prefix + "][" + suffix + "]{" + key + "}";
	}

}
