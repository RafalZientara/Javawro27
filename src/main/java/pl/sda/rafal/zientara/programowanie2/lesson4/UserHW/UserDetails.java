package pl.sda.rafal.zientara.programowanie2.lesson4.UserHW;

public class UserDetails {

    private String name;
    private int age;
    private String email;
    private int phone;


    public UserDetails(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public static class Builder {
        private String name;
        private int age;
        private String email;
        private int phone;

        private Builder(String name, int age, String email, int phone) {
            this.name = name;
            this.age = age;
            this.email = email;
            this.phone = phone;
        }
        public UserDetails build() {
            return new UserDetails(this);
        }
    }
}
