package com.example.backend.modelo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*Con estas lineas hacemos que Java reconcozca la clase Computer como
//si fuera una tabla, la informacion que hay en las tablas de la BD
se van a representar con instancias de las clases */
//Para poder pasar la informacion por este medio hay que heredar implements Serializable
//Aqui se definen las Clases Entidad para coonstruir las tablas de la Base de Datos.
//que si creamos en Mysql, el JPA es el que crea las tablas
@Entity
@Table(name = "Computer") //Aqui se indica que Computer es una tabla
public class Computer implements Serializable {
    //Le indicamos a java que la llave primaria es el id
    @Id
    //Par aque sea auto incrementable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //Definimos los campos de la tabla
    private Integer id;
    private String name;
    private String brand;
    private Integer year;
    private String description;
    //Aqwui establecemos la relacion con la tabla  Category
    @ManyToOne
    //Le indicamos a la tabla Computer como se va a llamar la llave foranea
    //Cuando se emplea una lista no se usa esta sentencia
    @JoinColumn(name = "category")
    /*Con esta sentencia se genera una recursion infinita, por que cada llamado a
     Categorias vuelve y retorna la lista de Computeres que estan en esa Categoria
    Recursividad infinita que se corrige con @JsonIgnoreProperties("computers")
     computers" hace referencia al campo que se llama infinitamente, asi se ignora
     y no lo llama mas de una vez, por que cuando un Computer este mostrando su categoria
    que ignore el llamado al campo tipo lista computers que carga nuevamente Computer
    que tiene esa categoria @JsonIgnoreProperties("computers")
    categoria es un objeto y este objeto forma parte de los atributos de la Clase
     Computer y entonces  se cargan todos los atributos de ese objeto tambien con los atributos
    basicos de la clase */
    @JsonIgnoreProperties("computers")
    private Category category;
    /*@OneToMany(mappedBy = "computers", cascade = CascadeType.ALL, orphanRemoval = true
    )*/    //Le indicamos a la tabla Computer como se va a llamar la llave foranea
    //Relacion entre Computer con Messagge como es Uno a Muchos debe tener un atributo tipo Lista Message
    //No debe emplear @JoinColumn(name="messages")
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "computer")
    private List<Message> messages;
    //Relacion entre Computer con Reservatio como es Uno a Muchos debe tener un atributo tipo Lista Reservation
    @OneToMany
    @JoinColumn(name = "reservations")
    @JsonIgnoreProperties({"message","category"})
    private List<Reservation> reservations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
