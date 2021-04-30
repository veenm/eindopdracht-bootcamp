package nl.veenm.novi.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static nl.veenm.novi.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    MANAGER(Sets.newHashSet(CUSTOMER_READ,CUSTOMER_WRITE,PLACEDORDER_READ,PLACEDORDER_WRITE)),
    CHEF(Sets.newHashSet(PLACEDORDER_READ,DELIVERY_WRITE)),
    COURIER(Sets.newHashSet(DELIVERY_READ)),
    CUSTOMER(Sets.newHashSet(PLACEDORDER_WRITE,PLACEDORDER_READ,DELIVERY_READ));


    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.permissions));
        return permissions;
    }
}
