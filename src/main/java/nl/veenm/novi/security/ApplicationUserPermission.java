package nl.veenm.novi.security;

public enum ApplicationUserPermission {
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    PLACEDORDER_READ("placedOrder:read"),
    PLACEDORDER_WRITE("placedOrder:write"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    DELIVERY_READ("delivery:read"),
    DELIVERY_WRITE("delivery:write"),
    PLACEDORDERDETAILS_READ("placedOrderDetails:read"),
    PLACEDORDERDETAILS_WRITE("placedOrderDetails:write");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
