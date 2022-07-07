package app.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "role")
@Getter
@Setter
@RequiredArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "role_permission",
            joinColumns = {@JoinColumn(name = "role_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "permission_id", nullable = false)}
    )
    Set<UserPermission> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(userPermission -> new SimpleGrantedAuthority(userPermission.getName()))
                .collect(Collectors.toSet());
    }
}
