package jsp.alternative.bean;

public final class Product {
    
    private String url;
    private String name;
    
    public Product() {
    }
    public Product(String url, String name) {
	this.url = url;
	this.name = name;
    }
    
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
