package myproject;

public class SearchDto {
	private String searchKey;
	private String searchTxt;

	public SearchDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchDto(String searchKey, String searchTxt) {
		super();
		this.searchKey = searchKey;
		this.searchTxt = searchTxt;
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
		return "SearchDto [searchKey=" + searchKey + ", searchTxt=" + searchTxt + "]";
	}
	
		
}
