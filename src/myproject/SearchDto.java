package myproject;

public class SearchDto {
	private String searchKey;
	private String searchTxt;
	private String searchArray;

	public SearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchDto(String searchKey, String searchTxt) {
		super();
		this.searchKey = searchKey;
		this.searchTxt = searchTxt;
	}

	
	public SearchDto(String searchKey, String searchTxt, String searchArray) {
		super();
		this.searchKey = searchKey;
		this.searchTxt = searchTxt;
		this.searchArray = searchArray;
	}

	public String getSearchArray() {
		return searchArray;
	}

	public void setSearchArray(String searchArray) {
		this.searchArray = searchArray;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchTxt() {
		return searchTxt;
	}

	public void setSearchTxt(String searchTxt) {
		this.searchTxt = searchTxt;
	}

	@Override
	public String toString() {
		return "SearchDto [searchKey=" + searchKey + ", searchTxt=" + searchTxt + ", searchArray=" + searchArray + "]";
	}
	
		
}
