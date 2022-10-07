package app.model.product.option;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "checked_user_option")
public class CheckedUserOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ElementCollection
    private List<Long> optionIds = new ArrayList<>();
}
