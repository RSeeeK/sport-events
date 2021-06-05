package ru.devhack.motomoto.sportevents.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.devhack.motomoto.sportevents.db.meta.SportEventsDBMeta;
import ru.devhack.motomoto.sportevents.model.UserModel;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = SportEventsDBMeta.schema, name = SportEventsDBMeta.user.name)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = SportEventsDBMeta.user.fld.id)
    private UUID id;

    @Column(name = SportEventsDBMeta.user.fld.employee_code)
    private String employeeCode;

    @Column(name = SportEventsDBMeta.user.fld.password)
    private String password;

    @Column(name = SportEventsDBMeta.user.fld.first_name)
    private String firstName;

    @Column(name = SportEventsDBMeta.user.fld.middle_name)
    private String middleName;

    @Column(name = SportEventsDBMeta.user.fld.last_name)
    private String lastName;

    @Column(name = SportEventsDBMeta.user.fld.image_url)
    private String imageUrl;

    @Column(name = SportEventsDBMeta.user.fld.role)
    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return employeeCode;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
