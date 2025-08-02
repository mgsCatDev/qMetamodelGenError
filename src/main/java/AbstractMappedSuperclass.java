
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public abstract class AbstractMappedSuperclass<T> {

    @Id
    private Long id;

    @NotNull
    private T something;

}
