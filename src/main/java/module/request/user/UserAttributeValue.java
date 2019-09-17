package module.request.user;

public class UserAttributeValue {
    private String name;

    public UserAttributeValue(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
