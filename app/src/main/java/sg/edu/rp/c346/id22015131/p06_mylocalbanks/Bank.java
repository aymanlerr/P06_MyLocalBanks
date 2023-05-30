package sg.edu.rp.c346.id22015131.p06_mylocalbanks;

public class Bank {
    private String name;
    private String url;
    private int contactNum;

    public Bank(String name, String url, int contactNum) {
        this.name = name;
        this.url = url;
        this.contactNum = contactNum;
    }
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getContactNum() {
        return contactNum;
    }
}
