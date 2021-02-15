package cz.dostalma.microserviceshop;

public interface Constants {

    enum RolesEnum {
        CUSTOMER("Customer"),
        ADMIN("Admin");

        private String name;

        RolesEnum(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    String REPOSITORY = "repository";
}
