package entities;

public class BaseEntity {
 //   @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
 //   @Column(name = "id", unique = true, nullable = false)
    private long id;

    public BaseEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}